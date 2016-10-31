package io.github.youngpeanut.designpatterns.proxy.android_hackIO;

import android.os.Looper;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.youngpeanut.designpatterns.utils.ReflectUtils;

/**
 * 监控项目中所有UI线程的IO操作
 * Created by chenshao on 16/10/20.
 * https://github.com/MShareGroup/MShare_Events/tree/master/%E7%AC%AC%E4%BA%8C%E6%9C%9F_Android_20160702/%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86%E9%A1%B9%E7%9B%AE%E5%AE%9E%E6%88%98
 *
 * 另一个大型实例 DroidPlugin
 * https://github.com/Qihoo360/DroidPlugin/blob/master/project/Libraries/DroidPlugin/src/com/morgoo/droidplugin/hook/binder/BinderHook.java
 */
public class IOAsyncMonitor implements InvocationHandler {
    private final static String TAG = "TAG_IOAsyncMonitor";

    private static IOAsyncMonitor mInstance;

    private Object old_os; // libcore.io.Os

    private static ExecutorService newSingleThreadExecutor;

    private IOAsyncMonitor() {
    }

    public synchronized static IOAsyncMonitor getInstance() {
        if (mInstance == null) {
            mInstance = new IOAsyncMonitor();
            newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        }
        return mInstance;
    }


    /**
     * 对外初始化方法
     * 安装拦截器
     */
    public void install() {

        Class class_LibCore = null;
        try {
            /**
             * IO操作的系统类
             * https://android.googlesource.com/platform/libcore/+/jb-mr2-release/luni/src/main/java/libcore/io/Libcore.java
             * public static Os os = new BlockGuardOs(new Posix());
             */
            class_LibCore = Class.forName("libcore.io.Libcore");
        } catch (ClassNotFoundException e) {
            // ingore
        }
        old_os = ReflectUtils.getStaticFieldValue(class_LibCore, "os");

        if (old_os == null) {
            Log.d(TAG, "old_os = null");
            return;
        }

        Class<?> class_os = old_os.getClass();
        Class<?>[] interfaces = getAllInterfaces(class_os);
        Object new_os = Proxy.newProxyInstance(class_os.getClassLoader(), interfaces, this);
        ReflectUtils.writeField(class_LibCore, "os", null, new_os);
    }

    /**
     * 循环遍历该类的所有父类的接口
     * @param clz
     * @return
     */
    private Class<?>[] getAllInterfaces(Class clz) {
        ArrayList<Class> re = new ArrayList<Class>();
        Class<?>[] ifss = clz.getInterfaces();
        for (Class<?> ifs : ifss) {
            if (!re.contains(ifs)) {
                re.add(ifs);
            }
        }

        Class superclass = clz.getSuperclass();
        while (superclass != null) {
            Class<?>[] sifss = superclass.getInterfaces();
            for (Class<?> ifs : sifss) {
                if (!re.contains(ifs)) {
                    re.add(ifs);
                }
            }
            superclass = superclass.getSuperclass();
        }
        return re.toArray(new Class[re.size()]);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            doInterrupt();
        }

        Object invoke = null;
        try {
            invoke = method.invoke(old_os, args);
        } catch (Throwable e) {
            throw e.getCause();
        }
        return invoke;
    }


    /**
     * 上报服务器
     */
    public void doInterrupt() {

    }
}

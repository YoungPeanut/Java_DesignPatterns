正在做项目的时候，发现Retrofit的APIService类只能是
没有继承关系的接口，见 Utils.validateServiceInterface(service);
不能有继承，解释是说Android系统Bug：http://b.android.com/58753
在这个页面看到了Proxy.newProxyInstance()，就开始看看代理。。。

#### 静态代理
==几个问题？
1，两个人都要实现IProxy？
2，Proxyable的构造器和方法都是public，可访问，
这样代理Proxy还有什么意义？
</br>
#### 动态代理
1 同样的问题：IFoo fooImpl = new FooImpl();
2 可以实现其他功能，例如在调用实现类方法前后加入Log，实现安全认证等。
#### android
1 《Android源码设计模式解析与实战》第18章 ＋ 示例
基本类型 返回long
https://github.com/simple-android-framework-exchange/android_design_patterns_analysis/tree/master/proxy/singwhatiwanna
封装类型 返回Book implements Parcelable
https://github.com/singwhatiwanna/android-art-res/blob/master/Chapter_2/src/com/ryg/chapter_2/manualbinder/BookManagerImpl.java

2 https://github.com/android/platform_frameworks_base/blob/master/core/java/android/app/ActivityManagerNative.java

3 android.support.v7.app.AppCompatActivity为了兼容不同android版本，把具体的实现委托给了AppCompatDelegate

    private static AppCompatDelegate create(Context context, Window window,
            AppCompatCallback callback) {
        final int sdk = Build.VERSION.SDK_INT;
        if (BuildCompat.isAtLeastN()) {
            return new AppCompatDelegateImplN(context, window, callback);
        } else if (sdk >= 23) {
            return new AppCompatDelegateImplV23(context, window, callback);
        } else if (sdk >= 14) {
            return new AppCompatDelegateImplV14(context, window, callback);
        } else if (sdk >= 11) {
            return new AppCompatDelegateImplV11(context, window, callback);
        } else {
            return new AppCompatDelegateImplV9(context, window, callback);
        }
    }

这是android中为了兼容一种常见的trick，_Compat结尾的support包里的类都有体现
类似的 https://github.com/maoruibin/TranslateApp/blob/master/app/src/main/java/name/gudong/translate/listener/clipboard/ClipboardManagerCompat.java
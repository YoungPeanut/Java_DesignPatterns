package io.github.youngpeanut.designpatterns.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by chenshao on 16/10/18.
 */
public class DynamicProxyDemo {

    public static void main(String[] arg){
        IFoo fooImpl = new FooImpl();
        ProxyHandler proxyHandler = new ProxyHandler(fooImpl);
        /**
         * 参数：
         * 生成代理类的classloader
         * 代理类要实现的所有接口
         * 处理函数调用的handler
         */
        IFoo proxy = (IFoo) Proxy.newProxyInstance(IFoo.class.getClassLoader(),new Class[]{IFoo.class},proxyHandler);
        proxy.fun1();
    }
}

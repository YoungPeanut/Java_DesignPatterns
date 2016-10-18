package io.github.youngpeanut.designpatterns.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * Created by chenshao on 16/10/18.
 */
public class DynamicProxyDemo {

    public static void main(String[] arg){
        IFoo fooImpl = new FooImpl();
        ProxyHandler proxyHandler = new ProxyHandler(fooImpl);
        IFoo proxy = (IFoo) Proxy.newProxyInstance(IFoo.class.getClassLoader(),new Class[]{IFoo.class},proxyHandler);
        proxy.fun1();
    }
}

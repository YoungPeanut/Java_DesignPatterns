package io.github.youngpeanut.designpatterns.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenshao on 16/10/18.
 */
public class ProxyHandler implements InvocationHandler {

    private Object delegate;

    public ProxyHandler(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        System.out.println("before invoke :"+method);
        method.invoke(delegate,objects);
        System.out.println("after invoke :"+method);

        return null;
    }
}

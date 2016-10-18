package io.github.youngpeanut.designpatterns.proxy.statics;

/**
 * 代理：中介
 * Created by chenshao on 16/10/18.
 */
public class Proxy implements IProxy {

    private Proxyable proxyable;

    public Proxy() {
        proxyable = new Proxyable();
    }

    @Override
    public void fun1() {
        System.out.println("before");
        proxyable.fun1();
        System.out.println("after");
    }
}

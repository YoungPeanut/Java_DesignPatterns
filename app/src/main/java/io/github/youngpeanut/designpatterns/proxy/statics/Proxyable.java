package io.github.youngpeanut.designpatterns.proxy.statics;

/**
 * 被代理者
 * Created by chenshao on 16/10/18.
 */
public class Proxyable implements IProxy {
    @Override
    public void fun1() {
        System.out.println("fun1: do something");
    }
}

package io.github.youngpeanut.designpatterns.singleton;

/**
 * 1 volatile是用来保证多个线程并发时，访问的都是内存中的同一个volatile对象。
 * 2 缺点 : 可以通过反射等方式产生多个对象！
 *
 * Created by chenshao on 16/11/2.
 */
public class SyncSingleton {
    private static volatile SyncSingleton instance;

    private SyncSingleton() {
    }

    public static SyncSingleton getInstance() {
        if (null == instance) {
            synchronized (SyncSingleton.class) {
                if (null == instance) {
                    instance = new SyncSingleton();
                }
            }
        }
        return instance;
    }
}

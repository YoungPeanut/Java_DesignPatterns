package io.github.youngpeanut.designpatterns.singleton;

/**
 * Created by chenshao on 16/11/2.
 */
public class Singleton2 {
    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }

    public static class SingletonHolder {
        public static final Singleton2 instance = new Singleton2();
    }
}

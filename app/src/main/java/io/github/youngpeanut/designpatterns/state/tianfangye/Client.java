package io.github.youngpeanut.designpatterns.state.tianfangye;

/**
 * https://zhuanlan.zhihu.com/p/23293088
 * Thx
 *
 * Created by chenshao on 16/10/31.
 */
public class Client {

    public static void main(String[] args) {
        Person person = new Person();
        person.takeAction();
        person.convertState();
        person.takeAction();
    }
}

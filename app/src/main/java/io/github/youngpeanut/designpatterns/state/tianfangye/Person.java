package io.github.youngpeanut.designpatterns.state.tianfangye;

/**
 *
 * Created by chenshao on 16/10/31.
 */
public class Person {
    // 当前状态
    private IState state;

    public Person() {
        this.state = new BruceState();
    }

    // 设置当前状态
    public void changeState(IState state) {
        this.state = state;
    }

    // 改变状态（变身）
    public void convertState() {
        this.state.convertState(this);
    }

    // 开始行动
    public void takeAction() {
        this.state.doActivities();
    }
}

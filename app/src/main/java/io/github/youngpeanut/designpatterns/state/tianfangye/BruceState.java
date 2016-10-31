package io.github.youngpeanut.designpatterns.state.tianfangye;

/**
 * Created by chenshao on 16/10/31.
 */
public class BruceState implements IState {
    private String name = "- Bruce -";

    // 转换状态
    public void convertState(Person person) {
        person.changeState(new BatmanState());
    }

    // 执行活动
    public void doActivities() {
        System.out.println(this.name + " <> " + "参加宴会");
    }

}

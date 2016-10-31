package io.github.youngpeanut.designpatterns.state.tianfangye;

/**
 * Created by chenshao on 16/10/31.
 */
public class BatmanState implements IState {
    private String name = "- Batman -";

    // 转换状态
    public void convertState(Person person) {
        person.changeState(new BruceState());
    }

    // 执行活动
    public void doActivities() {
        System.out.println(this.name + " <> " + "打击犯罪");
    }
}

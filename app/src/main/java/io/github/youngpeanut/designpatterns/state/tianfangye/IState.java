package io.github.youngpeanut.designpatterns.state.tianfangye;

/**
 * Created by chenshao on 16/10/31.
 */
public interface IState {
    // 转换状态
    public void convertState(Person person);

    // 执行活动
    public void doActivities();
}

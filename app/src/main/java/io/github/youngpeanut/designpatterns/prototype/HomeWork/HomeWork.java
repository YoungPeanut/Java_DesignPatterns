package io.github.youngpeanut.designpatterns.prototype.HomeWork;

/**
 * Created by chenshao on 16/11/2.
 */
public class HomeWork implements Cloneable {
    private String data;

    public HomeWork(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

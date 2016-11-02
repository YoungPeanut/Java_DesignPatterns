package io.github.youngpeanut.designpatterns.prototype.HomeWork;

/**
 * Created by chenshao on 16/11/2.
 */
public class Student implements Cloneable {
    private int age;
    private String name;
    private HomeWork homework;

    public Student(int age, String name, HomeWork homework) {
        this.age = age;
        this.name = name;
        this.homework = homework;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeWork getHomework() {
        return homework;
    }

    public void setHomework(HomeWork homework) {
        this.homework = homework;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student student = (Student)super.clone();
        student.setHomework((HomeWork)student.getHomework().clone());
        return student;
    }
}

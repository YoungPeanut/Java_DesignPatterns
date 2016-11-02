package io.github.youngpeanut.designpatterns.prototype.HomeWork;

/**
 * http://www.tuicool.com/articles/qQjIbq
 *
 * Created by chenshao on 16/11/2.
 */
public class WorkTest {
    
    public static void main(String[] args) throws Exception {
        Student student = new Student(1,"zhangsan",new HomeWork("语文作业"));
        Student student1= (Student)student.clone();
        Student student2 = (Student)student.clone();

        student2.getHomework().setData("语文作业（抄完了）");


        System.out.println(student.getHomework().getData());
        System.out.println(student1.getHomework().getData());
        System.out.println(student2.getHomework().getData());
    }
}

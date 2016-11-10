package io.github.youngpeanut.designpatterns.TemplateMethod.demo1;

/**
 *  A test client
 */
public class Test  {
    public static void main(String[] args) {
        // You should change the path of "README.md" in your local machine
        String fileName = "..\\README.md";
        String url = "http://www.github.com";
        
        AbstractRead fileRead = new ReadFile();
        AbstractRead htmlRead = new ReadHtml();

        fileRead.setResource(fileName);
        htmlRead.setResource(url);
        
        System.out.println("-----  Read from a file  -----");        
        fileRead.getContent();
        System.out.println("-----  Read from a url  -----");
        htmlRead.getContent();
    }
}
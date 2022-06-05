package adapter;

/**
 * 需要适配的类
 */
public class Adaptee {
    public void specialRequest() {
        System.out.println("specialRequest() | this is a real request from adaptee!");
    }
}

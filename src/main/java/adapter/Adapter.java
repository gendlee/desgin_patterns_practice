package adapter;

/**
 * 对象适配器
 */
public class Adapter implements Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        // 通过specialRequest 对 request 进行适配
        adaptee.specialRequest();
    }
}

package adapter;

/**
*@Desc: 类适配器
*/
public class Adapter2 extends Adaptee implements Target {
    @Override
    public void request() {
        this.specialRequest();
    }
}

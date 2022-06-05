package adapter;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void testAdapter() {
        Target target = new Adapter();
        target.request();
    }

    @Test
    public void testAdapter2() {
        Target target = new Adapter2();
        target.request();
    }
}

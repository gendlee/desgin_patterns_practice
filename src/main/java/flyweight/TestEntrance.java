package flyweight;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        FlyweightFactory flyweightFactory = new FlyweightFactory();

        Flyweight fw1 =  flyweightFactory.getFlyweight("one");
        fw1.operate();

        Flyweight fw2 = flyweightFactory.getFlyweight("two");
        fw2.operate();

        Flyweight fw3 = flyweightFactory.getFlyweight("one");
        fw3.operate();

    }
}

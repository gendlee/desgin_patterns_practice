package facadepattern;

import facadepattern.facade.Computer;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Computer computer = new Computer();
        computer.startup();
        System.out.println("------");
        computer.shutdown();
    }
}

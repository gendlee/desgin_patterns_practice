package decorator;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Transform transform = new Car();
        transform.move();

        Robot robot = new Robot(transform);
        robot.say();
        robot.move();

        AirPlane airPlane = new AirPlane(transform);
        airPlane.fly();
        airPlane.move();
    }
}

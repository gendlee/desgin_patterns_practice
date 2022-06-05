package bridgepattern;

import bridgepattern.color.Blue;
import bridgepattern.color.Green;
import bridgepattern.shape.Circle;
import bridgepattern.shape.Ellipse;
import bridgepattern.shape.Rectangle;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testGreen() {
        // 创建一个绿色
        Color green = new Green();
        // 创建一个圆形/椭圆
        Shape ellipse = new Ellipse();
        Shape circle = new Circle();
        // 上色
        ellipse.setColor(green);
        circle.setColor(green);

        // 打印
        ellipse.draw();
        circle.draw();
    }

    @Test
    public void testBlue() {
        // 创建一个蓝色
        Color blue = new Blue();
        // 创建一个矩形
        Shape rectangle = new Rectangle();
        // 上色
        rectangle.setColor(blue);

        // 打印
        rectangle.draw();
    }
}

package bridgepattern.shape;

import bridgepattern.Shape;

public class Ellipse extends Shape {
    @Override
    public void draw() {
        getColor().paint("椭圆");
    }
}

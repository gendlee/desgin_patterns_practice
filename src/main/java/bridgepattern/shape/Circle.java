package bridgepattern.shape;

import bridgepattern.Shape;

public class Circle extends Shape {
    @Override
    public void draw() {
        getColor().paint("圆");
    }
}

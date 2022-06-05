package bridgepattern.shape;

import bridgepattern.Shape;

public class Rectangle extends Shape {
    @Override
    public void draw() {
        getColor().paint("矩形");
    }
}

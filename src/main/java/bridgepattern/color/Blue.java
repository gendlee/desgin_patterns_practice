package bridgepattern.color;

import bridgepattern.Color;

public class Blue implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("蓝色的" + shape);
    }
}
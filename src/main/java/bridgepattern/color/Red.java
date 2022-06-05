package bridgepattern.color;

import bridgepattern.Color;

public class Red implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("红色的" + shape);
    }
}

package bridgepattern.color;

import bridgepattern.Color;

public class Green implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("绿色的" + shape);
    }
}

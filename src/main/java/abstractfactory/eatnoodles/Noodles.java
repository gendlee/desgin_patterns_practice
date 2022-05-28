package abstractfactory.eatnoodles;

import abstractfactory.Food;

public class Noodles extends Food {
    @Override
    public void name() {
        System.out.println("noodles");
    }
}

package abstractfactory.eatsteak;

import abstractfactory.Food;

/**
*@Desc: 食物-牛排
*/
public class Steak extends Food {
    @Override
    public void name() {
        System.out.println("steak.");
    }
}

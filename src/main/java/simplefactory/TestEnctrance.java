package simplefactory;

import org.junit.Test;

public class TestEnctrance {
    @Test
    public void testNoodles() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.NOODLES.name());

        food.name();
    }

    @Test
    public void testRice() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.RICE.name());

        food.name();
    }

    @Test
    public void testBeef() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.BEEF.name());

        food.name();
    }
}

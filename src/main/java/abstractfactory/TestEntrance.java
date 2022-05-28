package abstractfactory;

import abstractfactory.eatnoodles.EatNoodlesFactory;
import abstractfactory.eatsteak.EatSteakFactory;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testEatSteak() {
        AbstractFactory factory = new EatSteakFactory();
        // 人
        People people = factory.createPeople();
        // 餐具
        Tableware tableware = factory.createTableware();
        // 食物
        Food food = factory.createFood();

        people.who();
        tableware.use();
        food.name();
    }

    @Test
    public void testEatNoodles() {
        AbstractFactory factory = new EatNoodlesFactory();
        // 人
        People people = factory.createPeople();
        // 餐具
        Tableware tableware = factory.createTableware();
        // 食物
        Food food = factory.createFood();

        people.who();
        tableware.use();
        food.name();
    }
}

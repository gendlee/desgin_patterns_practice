package abstractfactory.eatnoodles;

import abstractfactory.AbstractFactory;
import abstractfactory.Food;
import abstractfactory.People;
import abstractfactory.Tableware;

public class EatNoodlesFactory extends AbstractFactory {
    @Override
    public People createPeople() {
        return new Chinese();
    }

    @Override
    public Tableware createTableware() {
        return new Chopsticks();
    }

    @Override
    public Food createFood() {
        return new Noodles();
    }
}

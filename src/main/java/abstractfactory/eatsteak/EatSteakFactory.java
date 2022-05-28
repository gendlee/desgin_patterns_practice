package abstractfactory.eatsteak;

import abstractfactory.AbstractFactory;
import abstractfactory.Food;
import abstractfactory.People;
import abstractfactory.Tableware;

public class EatSteakFactory extends AbstractFactory {
    @Override
    public People createPeople() {
        return new Americans();
    }

    @Override
    public Tableware createTableware() {
        return new Fork();
    }
    @Override
    public Food createFood() {
        return new Steak();
    }


}

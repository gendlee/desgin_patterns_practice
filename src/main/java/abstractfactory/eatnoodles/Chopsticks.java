package abstractfactory.eatnoodles;

import abstractfactory.Tableware;

public class Chopsticks extends Tableware {
    @Override
    public void use() {
        System.out.println("use chopsticks to eat");
    }
}

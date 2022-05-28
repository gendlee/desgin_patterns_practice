package abstractfactory.eatnoodles;

import abstractfactory.People;

public class Chinese extends People {
    @Override
    public void who() {
        System.out.println("Chinese");
    }
}

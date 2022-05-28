package abstractfactory.eatsteak;

import abstractfactory.People;
/**
 *@Desc: 人-美国人
 */
public class Americans extends People {
    @Override
    public void who() {
        System.out.println("Americans");
    }
}

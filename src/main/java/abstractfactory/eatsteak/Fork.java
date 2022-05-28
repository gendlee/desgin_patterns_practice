package abstractfactory.eatsteak;

import abstractfactory.Tableware;

/**
*@Desc: 餐具-餐叉
*/
public class Fork extends Tableware {
    @Override
    public void use() {
        System.out.println("use fork to eat");
    }
}

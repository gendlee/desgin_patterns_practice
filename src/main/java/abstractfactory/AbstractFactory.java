package abstractfactory;

abstract public class AbstractFactory {
    // 人
    public abstract People createPeople();
    // 餐具
    public abstract Tableware createTableware();
    // 食物
    public abstract Food createFood();
}

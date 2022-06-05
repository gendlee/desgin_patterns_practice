package flyweight;

public class ConcreteFlyweight extends Flyweight{
    private String state;

    public ConcreteFlyweight(String state) {
        this.state = state;
    }
    @Override
    public void operate() {
        System.out.println("Flyweight[" + state + "] 的operate在工作");
    }

}

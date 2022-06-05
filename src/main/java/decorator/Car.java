package decorator;

public class Car extends Transform {
    @Override
    public void move() {
        System.out.println("car move");
    }
}

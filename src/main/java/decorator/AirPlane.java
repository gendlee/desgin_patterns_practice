package decorator;

public class AirPlane extends Changer {
    private Transform transform;

    public AirPlane(Transform transform) {
        this.transform = transform;
    }

    @Override
    public void move() {
        System.out.println("airplane move");
    }

    // 扩展方法
    public void fly() {
        System.out.println("I can fly.");
    }
}

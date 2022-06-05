package decorator;

public class Robot extends Changer {
    private Transform transform;

    public Robot(Transform transform) {
        this.transform = transform;
    }

    @Override
    public void move() {
        System.out.println("robot move.");
    }

    // 扩展方法
    public void say() {
        System.out.println("I'm a robot now.");
    }


}

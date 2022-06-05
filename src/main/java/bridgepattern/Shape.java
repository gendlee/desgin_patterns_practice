package bridgepattern;

/**
*@Desc: 形状抽象类
*/
public abstract class Shape {
    // 颜色
    Color color;

    // 绘制颜色的抽象方法
    public abstract void draw();

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}

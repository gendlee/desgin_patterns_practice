package simplefactory;

public class FoodSimpleFactory extends SimpleFactory {
    @Override
    public Food createFood(String foodName) {
        if (FoodEnum.NOODLES.name().equals(foodName)) {
            return new Noodles();
        } else if (FoodEnum.RICE.name().equals(foodName)) {
            return new Rice();
        } else if (FoodEnum.BEEF.name().equals(foodName)) {
            return new Beef();
        } else {
            System.out.println("不支持的点餐类型");
            return null;
        }

    }
}

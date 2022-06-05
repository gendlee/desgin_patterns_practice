package flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlyweightFactory {
    Map<String, Flyweight> flyweightMap = new HashMap<>();

    public Flyweight getFlyweight(String str) {
        if (flyweightMap.get(str) == null) {
            Flyweight fw = new ConcreteFlyweight(str);
            flyweightMap.put(str, fw);
            return fw;
        } else {
            System.out.println("享元池中已经存在，直接使用");
            return flyweightMap.get(str);
        }
    }
}

package singleton;

import java.lang.reflect.Constructor;

public class SingletonByReflection {
    private static SingletonByReflection instance;

    static {
        try {
            Class<?> cl = Class.forName(SingletonByReflection.class.getName());
            //获得无参构造
            Constructor<?> con = cl.getDeclaredConstructor();
            //设置无参构造是可访问的
            con.setAccessible(true);
            //产生一个实例对象
            instance = (SingletonByReflection) con.newInstance();
        } catch (Exception e) {

        }
    }

    public static SingletonByReflection getInstance() {
        return instance;
    }
}

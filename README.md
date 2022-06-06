# 0010 设计模式-创建型模式

创建型模式是对类的实例化的抽象，对类的创建和使用分离。主要包括以下6种。

| 模式名 | 实践中用名 | 重要程度（最高为5星） |
| --- | --- | --- |
| 工厂方法模式 | Factory Method | * * * * *（5星） |
| 抽象工厂模式 | Abstract Factory | * * * * *（5星） |
| 单例模式 | Singleton | * * * *（4星） |
| 简单工厂模式 | Simple Factory | * * * *（4星） |
| 原型模式 | Prototype | * * * （3星） |
| 建造者模式 | Builder | * * （2星） |

## 1、工厂方法模式

### 1.1  定义

<aside>
🥝 定义：工厂父类负责定义创建产品对象的公共接口，由工厂子类负责生产具体产品对象，即将产品的创建延迟到工厂子类中完成，工厂子类来决定创建哪一种产品。

</aside>

工厂方法模式包含如下角色：

- Product：抽象产品
- ConcreteProduct：具体产品
- Factory：抽象工厂
- ConcreteFactory：具体工厂

- **UML图**

![FactoryMethod.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/419ba91f-de7e-425f-ba85-e5751e7b3fca/FactoryMethod.jpg)

- **时序图**

![seq_FactoryMethod.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/55095892-a941-42df-aaa6-5e2d57d42ef3/seq_FactoryMethod.jpg)

### 1.2 实践

> 实践用例：实现一个日志记录器，支持多种日志记录方式，如文件记录、数据库记录等，且用户可以根据要求动态选择日志记录方式， 现使用工厂方法模式设计该系统。
> 
- 创建一个日志工厂父类

```java
package factorymethod;

abstract public class LogFactory {
    abstract Log createLog();

}
```

其中，使用抽象的类，以及抽象的方法。现在让我们还需要一个抽象的日志类。

- 创建一个抽象的日志类

```java
package factorymethod;

abstract public class Log {
    abstract public void writeLog();
}
```

现在，抽象层的内容已经实现完毕，可以接活了。此时来了一个需求：做一个文件日志记录器，这个工具用来记录文件操作的日志。

有了抽象层的基础，我们要做的无非就是实现对应的两个的抽象类及其方法：

1、实现日志工厂子类

```java
package factorymethod;

public class FileLogFactory extends LogFactory{
    @Override
    public Log createLog() {
        System.out.println("create File Log Factory");
        return new FileLog();
    }
}
```

2、实现文件日志记录的具体方法

```java
package factorymethod;

public class FileLog extends Log{
    @Override
    public void writeLog() {
        System.out.println("File Log is writing...");
    }
}
```

我们来测试一下这个文件日志记录器：

```java
package factorymethod;

import org.junit.Test;

public class TestEntrance {

    @Test
    public void testFileLog() {
        LogFactory logFactory = new FileLogFactory();
        Log log = logFactory.createLog();

        log.writeLog();
    }

}
```

运行之后打印：

> create File Log Factory
File Log is writing...
> 

软件工程中，唯一不变的就是变化。现在新的需求来了，我们需要一个数据库日志记录器：专门记录日志操作的的日志。这简直就是个A-ha时刻，因为我们可以如法炮制。

1、实现一个数据库日志工厂子类

```java
package factorymethod;

public class DBLogFactory extends LogFactory{
    @Override
    public Log createLog() {
        System.out.println("create DB Log Factory");
        return new DBLog();
    }
}
```

2、实现数据库日志记录的具体方法

```java
package factorymethod;

public class DBLog extends Log {
    @Override
    public void writeLog() {
        System.out.println("DB Log is writing...");
    }
}
```

测试一下数据库日志记录器：

```java
package factorymethod;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void testDBLog() {
        LogFactory logFactory = new DBLogFactory();
        Log log = logFactory.createLog();

        log.writeLog();
    }
}
```

运行之后打印：

> create DB Log Factory
DB Log is writing...
> 

### 1.3 **工厂方法模式的优点**

- 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。
- 基于工厂角色和产品角色的多态性设计是工厂方法模式的关键。它能够使工厂可以自主确定创建何种产品对象，而如何创建这个对象的细节则完全封装在具体工厂内部。工厂方法模式之所以又被称为多态工厂模式，是因为所有的具体工厂类都具有同一抽象父类。
- 使用工厂方法模式的另一个优点是在系统中加入新产品时，无须修改抽象工厂和抽象产品提供的接口，无须修改客户端，也无须修改其他的具体工厂和具体产品，而只要添加一个具体工厂和具体产品就可以了。这样，系统的可扩展性也就变得非常好，完全符合“开闭原则”。

### 1.4 **工厂方法模式的缺点**

- 在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度，有更多的类需要编译和运行，会给系统带来一些额外的开销。
- 由于考虑到系统的可扩展性，需要引入抽象层，在客户端代码中均使用抽象层进行定义，增加了系统的抽象性和理解难度，且在实现时可能需要用到DOM、反射等技术，增加了系统的实现难度。

## 2、抽象工厂模式

在工厂方法模式中具体工厂负责生产具体的产品，每一个具体工厂对应一种具体产品，工厂方法也具有唯一性，一般情况下，一个具体工厂中只有一个工厂方法或者一组重载的工厂方法。但是有时候我们需要一个工厂可以提供多个产品对象，而不是单一的产品对象。

什么时候适合使用**抽象工厂模式**呢？

- **当系统所提供的工厂所需生产的具体产品并不是一个简单的对象**，而是多个位于不同产品等级结构中属于不同类型的具体产品时需要使用抽象工厂模式。
- 抽象工厂模式**是所有形式的工厂模式中最为抽象和最具一般性**的一种形态。
- 抽象工厂模式与工厂方法模式最大的区别在于，**工厂方法模式针对的是一个产品等级结构，而抽象工厂模式则需要面对多个产品等级结构**，一个工厂等级结构可以负责多个不同产品等级结构中的产品对象的创建 。当一个工厂等级结构可以创建出分属于不同产品等级结构的一个产品族中的所有对象时，抽象工厂模式比工厂方法模式更为简单、有效率。

### 2.1 定义

<aside>
🥝 抽象工厂模式(Abstract Factory Pattern)：提供一个创建一系列相关或相互依赖对象的接口，而无须指定它们具体的类。抽象工厂模式又称为Kit模式，属于对象创建型模式。

</aside>

抽象工厂模式包含如下角色：

- AbstractFactory：抽象工厂
- ConcreteFactory：具体工厂
- AbstractProduct：抽象产品
- Product：具体产品

- **UML图**

![AbatractFactory.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8f989450-782e-4d0f-b182-cb2706a4e1b1/AbatractFactory.jpg)

- **时序图**

![seq_AbatractFactory.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2cf1ef02-c247-440d-9933-c80a9f2b602a/seq_AbatractFactory.jpg)

### 2.2 实践

> 实践用例：用抽象类分别实现：人，餐具、食物。创建一个工厂子类，可以描述世界上的人用什么餐具来吃什么食物。比如：美国人用餐叉吃牛排，中国人用筷子吃面条。
> 
- 创建一个抽象工厂父类

```java
package abstractfactory;

abstract public class AbstractFactory {
    // 人
    public abstract People createPeople();
    // 餐具
    public abstract Tableware createTableware();
    // 食物
    public abstract Food createFood();
}
```

其中，人的抽象类：

```java
package abstractfactory;
/**
*@Desc: 人抽象类
*/
abstract public class People {
    abstract public void who();
}
```

餐具抽象类：

```java
package abstractfactory;
/**
*@Desc: 餐具抽象类
*/
abstract public class Tableware {
    abstract public void use();
}
```

食物抽象类：

```java
package abstractfactory;
/**
*@Desc: 食物抽象类
*/
abstract public class Food {
    abstract public void name();
}
```

- 实现一个吃牛排的工厂类

```java
package abstractfactory.eatsteak;

import abstractfactory.AbstractFactory;
import abstractfactory.Food;
import abstractfactory.People;
import abstractfactory.Tableware;

public class EatSteakFactory extends AbstractFactory {
    @Override
    public People createPeople() {
        return new Americans();
    }

    @Override
    public Tableware createTableware() {
        return new Fork();
    }
    @Override
    public Food createFood() {
        return new Steak();
    }

}
```

其中，实现对应的抽象类：

- 人类之美国人

```java
package abstractfactory.eatsteak;

import abstractfactory.People;
/**
 *@Desc: 人-美国人
 */
public class Americans extends People {
    @Override
    public void who() {
        System.out.println("Americans");
    }
}
```

- 餐具之餐叉

```java
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
```

- 食物之牛排

```java
package abstractfactory.eatsteak;

import abstractfactory.Food;

/**
*@Desc: 食物-牛排
*/
public class Steak extends Food {
    @Override
    public void name() {
        System.out.println("steak.");
    }
}
```

我们来测试美国人用餐叉吃牛排，执行测试：

```java
package abstractfactory;

import abstractfactory.eatsteak.EatSteakFactory;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testEatSteak() {
        AbstractFactory factory = new EatSteakFactory();
        // 人
        People people = factory.createPeople();
        // 餐具
        Tableware tableware = factory.createTableware();
        // 食物
        Food food = factory.createFood();

        people.who();
        tableware.use();
        food.name();

    }
}
```

**执行结果**：

> Americans
use fork to eat
steak.
> 

现在让我们来实现一下中国人用筷子吃面条。首选分别实现对应的抽象类：

- 实现一个吃面条的工厂子类

```java
package abstractfactory.eatnoodles;

import abstractfactory.AbstractFactory;
import abstractfactory.Food;
import abstractfactory.People;
import abstractfactory.Tableware;

public class EatNoodlesFactory extends AbstractFactory {
    @Override
    public People createPeople() {
        return new Chinese();
    }

    @Override
    public Tableware createTableware() {
        return new Chopsticks();
    }

    @Override
    public Food createFood() {
        return new Noodles();
    }
}
```

其中

- 人类之中国人

```java
package abstractfactory.eatnoodles;

import abstractfactory.People;

public class Chinese extends People {
    @Override
    public void who() {
        System.out.println("Chinese");
    }
}
```

- 餐具之筷子

```java
package abstractfactory.eatnoodles;

import abstractfactory.Tableware;

public class Chopsticks extends Tableware {
    @Override
    public void use() {
        System.out.println("use chopsticks to eat");
    }
}
```

- 食物之面条

```java
package abstractfactory.eatnoodles;

import abstractfactory.Food;

public class Noodles extends Food {
    @Override
    public void name() {
        System.out.println("noodles");
    }
}
```

我们来测试美国人用餐叉吃牛排，执行测试：

```java
package abstractfactory;

import abstractfactory.eatnoodles.EatNoodlesFactory;
import abstractfactory.eatsteak.EatSteakFactory;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testEatNoodles() {
        AbstractFactory factory = new EatNoodlesFactory();
        // 人
        People people = factory.createPeople();
        // 餐具
        Tableware tableware = factory.createTableware();
        // 食物
        Food food = factory.createFood();

        people.who();
        tableware.use();
        food.name();
    }
}
```

**执行结果**：

> Chinese
use chopsticks to eat
noodles
> 

### 2.3 抽象工厂模式的**优点**

- 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易。所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。另外，应用抽象工厂模式可以实现高内聚低耦合的设计目的，因此抽象工厂模式得到了广泛的应用。
- 当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。这对一些需要根据当前环境来决定其行为的软件系统来说，是一种非常实用的设计模式。
- 增加新的具体工厂和产品族很方便，无须修改已有系统，符合“开闭原则”。

### 2**.4 缺点**

- 在添加新的产品对象时，难以扩展抽象工厂来生产新种类的产品，这是因为在抽象工厂角色中规定了所有可能被创建的产品集合，要支持新种类的产品就意味着要对该接口进行扩展，而这将涉及到对抽象工厂角色及其所有子类的修改，显然会带来较大的不便。
- 开闭原则的倾斜性（增加新的工厂和产品族容易，增加新的产品等级结构麻烦）。

## 3、单例模式

对于系统中的某些类来说，只有一个实例很重要，例如，一个系统中可以存在多个打印任务，但是只能有一个正在工作的任务；一个系统只能有一个窗口管理器或文件系统；一个系统只能有一个计时工具或ID（序号）生成器。

如何保证一个类只有一个实例并且这个实例易于被访问呢？**定义一个全局变量可以确保对象随时都可以被访问，但不能防止我们实例化多个对象**。

一个更好的解决办法是**让类自身负责保存它的唯一实例**。这个类可以保证没有其他实例被创建，并且它可以提供一个访问该实例的方法。这就是单例模式的模式动机。

### 3.1  定义

<aside>
🥝 单例模式(Singleton Pattern)：单例模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例，这个类称为单例类，它提供全局访问的方法。

</aside>

单例模式的要点有三个：

- 一是某个类只能有一个实例；
- 二是它必须自行创建这个实例；
- 三是它必须自行向整个系统提供这个实例。

- **UML图**

![Singleton.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0717820e-d42d-4044-90ad-478da40b801f/Singleton.jpg)

- **时序图**

![seq_Singleton.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7feec790-25f7-4791-9e75-af077509003b/seq_Singleton.jpg)

目前单列类的实现，比较推荐的做法有两种：

- 双重校验锁方式（效率稍低）

```java
package singleton;

public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

- 通过反射实现（效率更高）

```java
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
```

- 枚举实现单例：

```java
package singleton;

public enum  EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
```

其他实现方式这里不做展开，在单例模式的实现过程中，需要注意如下三点：

- 单例类的构造函数为私有；
- 提供一个自身的静态私有成员变量；
- 提供一个公有的静态工厂方法。

### 3**.2 优点**

- 提供了对唯一实例的受控访问。因为单例类封装了它的唯一实例，所以它可以严格控制客户怎样以及何时访问它，并为设计及开发团队提供了共享的概念。
- 由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象，单例模式无疑可以提高系统的性能。
- 允许可变数目的实例。我们可以基于单例模式进行扩展，使用与单例控制相似的方法来获得指定个数的对象实例。

### 3**.3 缺点**

- 由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
- 单例类的职责过重，在一定程度上违背了“单一职责原则”。因为单例类既充当了工厂角色，提供了工厂方法，同时又充当了产品角色，包含一些业务方法，将产品的创建和产品的本身的功能融合到一起。
- 滥用单例将带来一些负面问题，如为了节省资源将数据库连接池对象设计为单例类，可能会导致共享连接池对象的程序过多而出现连接池溢出；现在很多面向对象语言(如Java、C#)的运行环境都提供了自动垃圾回收的技术，因此，如果实例化的对象长时间不被利用，系统会认为它是垃圾，会自动销毁并回收资源，下次利用时又将重新实例化，这将导致对象状态的丢失。

## 4、简单工厂模式

### 4.1 定义

<aside>
🥝 简单工厂模式(Simple Factory Pattern)：又称为静态工厂方法(Static Factory Method)模式，它属于类创建型模式。在简单工厂模式中，**可以根据参数的不同返回不同类的实例**。简单工厂模式专门定义一个类来负责创建其他类的实例，被创建的实例通常都具有共同的父类。

</aside>

简单工厂模式包含如下角色：

- **Factory：工厂角色，**工厂角色负责实现创建所有实例的内部逻辑
- **Product：抽象产品角色，**抽象产品角色是所创建的所有对象的父类，负责描述所有实例所共有的公共接口
- **ConcreteProduct：具体产品角色，**具体产品角色是创建目标，所有创建的对象都充当这个角色的某个具体类的实例。

- **UML图**

![SimpleFactory.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d37e2c4a-89cf-4f3c-b7a1-7400b9ea3c1b/SimpleFactory.jpg)

- **时序图**

![seq_SimpleFactory.jpg](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/818c427a-1a37-469d-96c1-b55bd1029c7e/seq_SimpleFactory.jpg)

### 4.2 实践

> 实现案例：模拟点餐场景，后厨根据用户点的食物名称，做对应的食物。
> 
- 编写一个简单工厂类：创建工厂通过传递参数`foodName`来创建不同的食物。

```java
package simplefactory;

abstract public class SimpleFactory {
    abstract public Food createFood(String foodName);
}
```

- 创建食物的抽象类

```java
package simplefactory;

abstract public class Food {
    abstract public void name();
}
```

现在厨房今日的菜单上有三个菜可选，分别为：面条，牛肉、米饭。我们分别创建对应的类：

- 面条

```java
package simplefactory;

public class Noodles extends Food {
    @Override
    public void name() {
        System.out.println("this is a bowl of noodles.");
    }
}
```

- 牛肉

```java
package simplefactory;

public class Beef extends Food {
    @Override
    public void name() {
        System.out.println("this is a piece of beef.");
    }
}
```

- 米饭

```java
package simplefactory;

public class Rice extends Food {
    @Override
    public void name() {
        System.out.println("this is a bowl of rice.");
    }
}
```

点餐工具就是一个简单工厂类的实现：

- 实现简单工厂类

```java
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
```

- 菜单类枚举

```java
package simplefactory;

public enum FoodEnum {
    RICE,
    NOODLES,
    BEEF
}
```

我们来测试一下，一个点了面条的场景：

```java
package simplefactory;

import org.junit.Test;

public class TestEnctrance {
    @Test
    public void testNoodles() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.NOODLES.name());

        food.name();
    }
}
```

**执行结果：**

> this is a bowl of noodles.
> 

点一碗米饭：

```java
package simplefactory;

import org.junit.Test;

public class TestEnctrance {
    @Test
    public void testRice() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.RICE.name());

        food.name();
    }
}
```

**执行结果：**

> this is a bowl of rice.
> 

 

点一份牛排

```java
package simplefactory;

import org.junit.Test;

public class TestEnctrance {
    @Test
    public void testBeef() {
        SimpleFactory simpleFactory = new FoodSimpleFactory();

        Food food = simpleFactory.createFood(FoodEnum.BEEF.name());

        food.name();
    }
}
```

**执行结果：**

> this is a piece of beef.
> 

### 4.3 **简单工厂模式的优点**

- 工厂类含有必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，客户端可以免除直接创建产品对象的责任，而仅仅“消费”产品；简单工厂模式通过这种做法实现了对责任的分割，它提供了专门的工厂类用于创建对象。
- 客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，对于一些复杂的类名，通过简单工厂模式可以减少使用者的记忆量。
- 通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。

### 4.4 **简单工厂模式的缺点**

- 由于工厂类集中了所有产品创建逻辑，一旦不能正常工作，整个系统都要受到影响。
- 使用简单工厂模式将会增加系统中类的个数，在一定程序上增加了系统的复杂度和理解难度。
- 系统扩展困难，一旦添加新产品就不得不修改工厂逻辑，在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展和维护。
- 简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。

## 5、原型模式

参考：

[原型模式](https://www.runoob.com/design-pattern/prototype-pattern.html)

## 6、建造者模式

这部分在之前的博客中有详细介绍：
https://gendlee.github.io/0006-java1-20-3117c9.html

## 7、文中代码

[https://github.com/gendlee/desgin_patterns_practice](https://github.com/gendlee/desgin_patterns_practice)

---

## 参考文献

本节将介绍设计模式中的另一个大类——结构性模式。

适配器模式(Adapter)重要程度：4
桥接模式(Bridge)重要程度：3
组合模式(Composite)重要程度：4
装饰模式(Decorator)重要程度：3
外观模式(Facade)重要程度：5
享元模式(Flyweight)重要程度：1
代理模式(Proxy)重要程度：4
1、适配器模式
1.1 定义
🥝
适配器模式(Adapter Pattern) ：将一个接口转换成客户希望的另一个接口，适配器模式使接口不兼容的那些类可以一起工作，其别名为包装器(Wrapper)。适配器模式既可以作为类结构型模式，也可以作为对象结构型模式。

适配器模式包含如下角色：

Target：目标抽象类     即我们可以直接使用的类
Adapter：适配器类      
Adaptee：适配者类    即我们不能直接使用的类
Client：客户类

适配器模式有对象适配器和类适配器两种实现：

对象适配器：


类适配器：

时序图

1.2 实践
我们来看一下代码实现。

首先编写 Target 接口(这是客户所期待的接口。目标可以是具体的或抽象的类，也可以是接口)代码如下：

package adapter;
/**
*@Desc: 目标类接口
*/
public interface Target {
    public void request();
}

需要适配的类Adaptee代码如下：

package adapter;

/**
 * 需要适配的类
 */
public class Adaptee {
    public void specialRequest() {
        System.out.println("specialRequest() | this is a real request from adaptee!");
    }
}

Adapter(通过在内部包装一个Adaptee对象，把源对象接口转换成目标接口)代码如下:

package adapter;

/**
 * 对象适配器
 */
public class Adapter implements Target {
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        // 通过specialRequest 对 request 进行适配
        adaptee.specialRequest();
    }
}
 因此我们使用的时候如下：

package adapter;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Target target = new Adapter();
        target.request();
    }
}
我们来测试一下：

package adapter;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Target target = new Adapter();
        target.request();
    }
}
输出：

specialRequest() | this is a real request from adaptee!

另一种实现方式是使用类适配器，与对象适配器模式不同的是，类适配器模式是使用继承关系连接到Adaptee类，而不是使用委派关系连接到Adaptee类。

package adapter;

/**
*@Desc: 类适配器
*/
public class Adapter2 extends Adaptee implements Target {
    @Override
    public void request() {
        this.specialRequest();
    }
}
测试一下：

package adapter;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void testAdapter2() {
        Target target = new Adapter2();
        target.request();
    }
}
输出与对象适配器完全一致。

1.3 优点
将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，而无须修改原有代码。
增加了类的透明性和复用性，将具体的实现封装在适配者类中，对于客户端类来说是透明的，而且提高了适配者的复用性。
灵活性和扩展性都非常好，通过使用配置文件，可以很方便地更换适配器，也可以在不修改原有代码的基础上增加新的适配器类，完全符合“开闭原则”。
类适配器模式还具有如下优点：

由于适配器类是适配者类的子类，因此可以在适配器类中置换一些适配者的方法，使得适配器的灵活性更强。

对象适配器模式还具有如下优点：一个对象适配器可以把多个不同的适配者适配到同一个目标，也就是说，同一个适配器可以把适配者类和它的子类都适配到目标接口。

1.4 缺点
类适配器模式的缺点如下：

对于Java、C#等不支持多重继承的语言，一次最多只能适配一个适配者类，而且目标抽象类只能为抽象类，不能为具体类，其使用有一定的局限性，不能将一个适配者类和它的子类都适配到目标接口。

对象适配器模式的缺点如下：与类适配器模式相比，要想置换适配者类的方法就不容易。如果一定要置换掉适配者类的一个或多个方法，就只好先做一个适配者类的子类，将适配者类的方法置换掉，然后再把适配者类的子类当做真正的适配者进行适配，实现过程较为复杂。


2. 桥接模式
考虑这样的场景：

要绘制矩形、圆形、椭圆、正方形，我们至少需要4个形状类，但是如果绘制的图形需要具有不同的颜色，如红色、绿色、蓝色等，此时至少有如下两种设计方案：

第一种设计方案是为每一种形状都提供一套各种颜色的版本。
第二种设计方案是根据实际需要对形状和颜色进行组合
对于有两个变化维度（即两个变化的原因）的系统，采用方案二来进行设计系统中类的个数更少，且系统扩展更为方便。设计方案二即是桥接模式的应用。桥接模式将继承关系转换为关联关系，从而降低了类与类之间的耦合，减少了代码编写量。

2.1 定义
🥝
桥接模式(Bridge Pattern)：将抽象部分与它的实现部分分离，使它们都可以独立地变化。它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。

桥接模式包含如下角色：

Abstraction：抽象类
RefinedAbstraction：扩充抽象类
Implementor：实现类接口
ConcreteImplementor：具体实现类

UML图

时序图

2.2 实践
我们用桥接模式来实现开头中提到的案例：

绘制3种图型：矩形、圆形、椭圆
可绘制的颜色有3种：红色、绿色、蓝色
如何用一个桥接模式来实现呢？

颜色接口：
package bridgepattern;

/**
*@Desc: 对传入的形状上色接口
*/
public interface Color {
    public void paint(String shape);
}

形状的抽象类
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
下面分别实现不同颜色：

绿色：
package bridgepattern.color;

import bridgepattern.Color;

public class Green implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("绿色的" + shape);
    }
}
红色
package bridgepattern.color;

import bridgepattern.Color;

public class Red implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("红色的" + shape);
    }
}
蓝色
package bridgepattern.color;

import bridgepattern.Color;

public class Blue implements Color {
    @Override
    public void paint(String shape) {
        System.out.println("蓝色的" + shape);
    }
}

下面分别实现不同形状：

圆
package bridgepattern.shape;

import bridgepattern.Shape;

public class Circle extends Shape {
    @Override
    public void draw() {
        getColor().paint("圆");
    }
}
椭圆
package bridgepattern.shape;

import bridgepattern.Shape;

public class Ellipse extends Shape {
    @Override
    public void draw() {
        getColor().paint("椭圆");
    }
}
矩形
package bridgepattern.shape;

import bridgepattern.Shape;

public class Rectangle extends Shape {
    @Override
    public void draw() {
        getColor().paint("矩形");
    }
}

我们要绘制一个绿色的圆和椭圆：

package bridgepattern;

import bridgepattern.color.Green;
import bridgepattern.shape.Circle;
import bridgepattern.shape.Ellipse;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testGreen() {
        // 创建一个绿色
        Color green = new Green();
        // 创建一个圆形/椭圆
        Shape ellipse = new Ellipse();
        Shape circle = new Circle();
        // 上色
        ellipse.setColor(green);
        circle.setColor(green);

        // 打印
        ellipse.draw();
        circle.draw();
    }
}
执行结果：

绿色的椭圆
绿色的圆
如果要绘制蓝色的矩形：

package bridgepattern;

import bridgepattern.color.Blue;
import bridgepattern.shape.Rectangle;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void testBlue() {
        // 创建一个蓝色
        Color blue = new Blue();
        // 创建一个矩形
        Shape rectangle = new Rectangle();
        // 上色
        rectangle.setColor(blue);
        
        // 打印
        rectangle.draw();
    }
}
执行结果：

蓝色的矩形

2.3 使用场景举例
🥝
如果需要开发一个跨平台视频播放器，可以在不同操作系统平台（如Windows、Linux、Unix等）上播放多种格式的视频文件，常见的视频格式包括MPEG、RMVB、AVI、WMV等。就可以使用桥接模式设计该播放器。


2.4 优点
桥接模式的优点:

分离抽象接口及其实现部分。
桥接模式有时类似于多继承方案，但是多继承方案违背了类的单一职责原则（即一个类只有一个变化的原因），复用性比较差，而且多继承结构中类的个数非常庞大，桥接模式是比多继承方案更好的解决方法。
桥接模式提高了系统的可扩充性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统。实现细节对客户透明，可以对用户隐藏实现细节。
2.5 缺点
桥接模式的缺点:

桥接模式的引入会增加系统的理解与设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编程。 
桥接模式要求正确识别出系统中两个独立变化的维度，因此其使用范围具有一定的局限性。

3、装饰者模式
一般有两种方式可以实现给一个类或对象增加行为：

继承机制，使用继承机制是给现有类添加功能的一种有效途径，通过继承一个现有类可以使得子类在拥有自身方法的同时还拥有父类的方法。但是这种方法是静态的，用户不能控制增加行为的方式和时机。
关联机制，即将一个类的对象嵌入另一个对象中，由另一个对象来决定是否调用嵌入对象的行为以便扩展自己的行为，我们称这个嵌入的对象为装饰器(Decorator)

3.1 定义
🥝
装饰模式(Decorator Pattern) ：动态地给一个对象增加一些额外的职责(Responsibility)，就增加对象功能来说，装饰模式比生成子类实现更为灵活。其别名也可以称为包装器(Wrapper)，与适配器模式的别名相同，但它们适用于不同的场合。根据翻译的不同，装饰模式也有人称之为“油漆工模式”，它是一种对象结构型模式。

装饰模式包含如下角色：

Component: 抽象构件
ConcreteComponent: 具体构件
Decorator: 抽象装饰类
ConcreteDecorator: 具体装饰类

UML图

时序图

3.2 实践
实例：变形金刚

变形金刚在变形之前是一辆汽车，它可以在陆地上移动。当它变成机器人之后除了能够在陆地上移动之外，还可以说话；如果需要，它还可以变成飞机，除了在陆地上移动还可以在天空中飞翔。

具有移动能力的变形金刚
package decorator;

public abstract class Transform {
    public abstract void move();
}
变成汽车
package decorator;

public class Car extends Transform {
    @Override
    public void move() {
        System.out.println("car move");
    }
}
提供一个装饰者changer
package decorator;

public abstract class Changer extends Transform{
    public abstract void move();
}
变成机器人：
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
变成飞机
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

测试一下：

package decorator;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Transform transform = new Car();
        transform.move();

        Robot robot = new Robot(transform);
        robot.say();
        robot.move();

        AirPlane airPlane = new AirPlane(transform);
        airPlane.fly();
        airPlane.move();
    }
}
执行结果：

car move
I'm a robot now.
robot move.
I can fly.
airplane move

JDK中的装饰者模式：

BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

3.3 优点
装饰模式的优点:

装饰模式与继承关系的目的都是要扩展对象的功能，但是装饰模式可以提供比继承更多的灵活性。
可以通过一种动态的方式来扩展一个对象的功能，通过配置文件可以在运行时选择不同的装饰器，从而实现不同的行为。
通过使用不同的具体装饰类以及这些装饰类的排列组合，可以创造出很多不同行为的组合。可以使用多个具体装饰类来装饰同一对象，得到功能更为强大的对象。
具体构件类与具体装饰类可以独立变化，用户可以根据需要增加新的具体构件类和具体装饰类，在使用时再对其进行组合，原有代码无须改变，符合“开闭原则”
3.4 缺点
装饰模式的缺点:

使用装饰模式进行系统设计时将产生很多小对象，这些对象的区别在于它们之间相互连接的方式有所不同，而不是它们的类或者属性值有所不同，同时还将产生很多具体装饰类。这些装饰类和小对象的产生将增加系统的复杂度，加大学习与理解的难度。
这种比继承更加灵活机动的特性，也同时意味着装饰模式比继承更加易于出错，排错也很困难，对于多次装饰的对象，调试时寻找错误可能需要逐级排查，较为烦琐。

4、外观模式
4.1 定义
🥝
外观模式(Facade Pattern)：外部与一个子系统的通信必须通过一个统一的外观对象进行，为子系统中的一组接口提供一个一致的界面，外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。外观模式又称为门面模式，它是一种对象结构型模式。

外观模式包含如下角色：

Facade: 外观角色
SubSystem:子系统角色

UML图

时序图

4.2 实践
假设一台电脑，它包含了 CPU（处理器），Memory（内存） ，Disk（硬盘）这几个部件，若想要启动电脑，则先后必须启动 CPU、Memory、Disk。关闭也是如此。

SubSystem 子系统角色：

CPU
package facadepattern.subsystem;

public class CPU {
    public void startup() {
        System.out.println("cpu startup!");
    }

    public void shutdown() {
        System.out.println("cpu shutdown!");
    }
}
Disk
package facadepattern.subsystem;

public class Disk {
    public void startup() {
        System.out.println("disk startup!");
    }

    public void shutdown() {
        System.out.println("disk shutdown!");
    }
}
Memery
package facadepattern.subsystem;

public class Memory {
    public void startup() {
        System.out.println("memory startup!");
    }

    public void shutdown() {
        System.out.println("memory shutdown!");
    }
}


Facade 外观角色:

package facadepattern.facade;

import facadepattern.subsystem.CPU;
import facadepattern.subsystem.Disk;
import facadepattern.subsystem.Memory;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new CPU();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {
        System.out.println("computer is starting...");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("computer started!");
    }

    public void shutdown() {
        System.out.println("computer is shutting down...");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("computer has shut down!");
    }
}

测试一下：

package facadepattern;

import facadepattern.facade.Computer;
import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Computer computer = new Computer();
        computer.startup();
        System.out.println("------");
        computer.shutdown();
    }
}

执行结果：

computer is starting...
cpu startup!
memory startup!
disk startup!
computer started!
computer is shutting down...
cpu shutdown!
memory shutdown!
disk shutdown!
computer has shut down!

4.3 优点
外观模式的优点

对客户屏蔽子系统组件，减少了客户处理的对象数目并使得子系统使用起来更加容易。通过引入外观模式，客户代码将变得很简单，与之关联的对象也很少。
实现了子系统与客户之间的松耦合关系，这使得子系统的组件变化不会影响到调用它的客户类，只需要调整外观类即可。
降低了大型软件系统中的编译依赖性，并简化了系统在不同平台之间的移植过程，因为编译一个子系统一般不需要编译所有其他的子系统。一个子系统的修改对其他子系统没有任何影响，而且子系统内部变化也不会影响到外观对象。
只是提供了一个访问子系统的统一入口，并不影响用户直接使用子系统类。
4.4 缺点
外观模式的缺点

不能很好地限制客户使用子系统类，如果对客户访问子系统类做太多的限制则减少了可变性和灵活性。
在不引入抽象外观类的情况下，增加新的子系统可能需要修改外观类或客户端的源代码，违背了“开闭原则”。

5、享元模式
5.1 定义
🥝
享元模式(Flyweight Pattern)：运用共享技术有效地支持大量细粒度对象的复用。系统只使用少量的对象，而这些对象都很相似，状态变化很小，可以实现对象的多次复用。由于享元模式要求能够共享的对象必须是细粒度对象，因此它又称为轻量级模式，它是一种对象结构型模式。


享元模式包含如下角色：

Flyweight: 抽象享元类
ConcreteFlyweight: 具体享元类
UnsharedConcreteFlyweight: 非共享具体享元类
FlyweightFactory: 享元工厂类

UML图

时序图


首先我们定义一个享元抽象类
package flyweight;

public abstract class Flyweight {
    public abstract void operate();

}
再实现一个具体的享元类
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

实现一个享元工厂类
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

我们来测试一下：

package flyweight;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        FlyweightFactory flyweightFactory = new FlyweightFactory();

        Flyweight fw1 =  flyweightFactory.getFlyweight("one");
        fw1.operate();

        Flyweight fw2 = flyweightFactory.getFlyweight("two");
        fw2.operate();

        Flyweight fw3 = flyweightFactory.getFlyweight("one");
        fw3.operate();

    }
}
执行结果：

Flyweight[one] 的operate在工作
Flyweight[two] 的operate在工作
享元池中已经存在，直接使用
Flyweight[one] 的operate在工作

可见，享元模式中，我们实现了已经有的对象的复用——在享元池中直接获取已存在的对象。


5.2 优点
享元模式的优点

享元模式的优点在于它可以极大减少内存中对象的数量，使得相同对象或相似对象在内存中只保存一份。
享元模式的外部状态相对独立，而且不会影响其内部状态，从而使得享元对象可以在不同的环境中被共享。
5.3 缺点
享元模式的缺点

享元模式使得系统更加复杂，需要分离出内部状态和外部状态，这使得程序的逻辑复杂化。
为了使对象可以共享，享元模式需要将享元对象的状态外部化，而读取外部状态使得运行时间变长。
5.4 模式应用
享元模式在编辑器软件中大量使用，如在一个文档中多次出现相同的图片，则只需要创建一个图片对象，通过在应用程序中设置该图片出现的位置，可以实现该图片在不同地方多次重复显示。


6、代理模式
在某些情况下，一个客户不想或者不能直接引用一个对 象，此时可以通过一个称之为“代理”的第三者来实现 间接引用。

代理对象可以在客户端和目标对象之间起到 中介的作用，并且可以通过代理对象去掉客户不能看到 的内容和服务或者添加客户需要的额外服务（如特性增强）。

通过引入一个新的对象（如小图片和远程代理 对象）来实现对真实对象的操作或者将新的对象作为真实对象的一个替身，这种实现机制即为代理模式，通过引入代理对象来间接访问一个对象，这就是代理模式的模式动机。


6.1 定义
🥝
代理模式(Proxy Pattern) ：给某一个对象提供一个代理，并由代理对象控制对原对象的引用。


代理模式包含如下角色：

Subject: 抽象主题角色
Proxy: 代理主题角色
RealSubject: 真实主题角色

UML图

时序图

6.2 实践
实现一个被代理的对象subject，其功能是发送一个请求。
package proxy;

public class Subject {
    public void request() {
        System.out.println("这是真实发出的请求");
    }
}
为subject创建一个代理类proxy：
package proxy;

public class Proxy extends Subject {
    @Override
    public void request() {
        preRequest();
        super.request();
        afterRequest();
    }

    private void preRequest() {
        System.out.println("preRequest：发送请求前先处理一下");
    }

    private void afterRequest() {
        System.out.println("afterRequest: 请求完再处理一下");
    }
}
测试一下：

package proxy;

import org.junit.Test;

public class TestEntrance {
    @Test
    public void test() {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
执行结果：

preRequest：发送请求前先处理一下
这是真实发出的请求
afterRequest: 请求完再处理一下

可见，我们对subject做了代理后，在代理中可以插入一些方法，实现其他目的。


6.3 优点
代理模式的优点

代理模式能够协调调用者和被调用者，在一定程度上降低了系 统的耦合度。
远程代理使得客户端可以访问在远程机器上的对象，远程机器 可能具有更好的计算性能与处理速度，可以快速响应并处理客户端请求。
虚拟代理通过使用一个小对象来代表一个大对象，可以减少系 统资源的消耗，对系统进行优化并提高运行速度。
保护代理可以控制对真实对象的使用权限。
6.4 缺点
代理模式的缺点

由于在客户端和真实主题之间增加了代理对象，因此 有些类型的代理模式可能会造成请求的处理速度变慢。
实现代理模式需要额外的工作，有些代理模式的实现 非常复杂。

6.5 适用环境
根据代理模式的使用目的，常见的代理模式有以下几种类型：

远程(Remote)代理：为一个位于不同的地址空间的对象提供一个本地 的代理对象，这个不同的地址空间可以是在同一台主机中，也可是在 另一台主机中，远程代理又叫做大使(Ambassador)。
虚拟(Virtual)代理：如果需要创建一个资源消耗较大的对象，先创建一个消耗相对较小的对象来表示，真实对象只在需要时才会被真正创建。
Copy-on-Write代理：它是虚拟代理的一种，把复制（克隆）操作延迟 到只有在客户端真正需要时才执行。一般来说，对象的深克隆是一个 开销较大的操作，Copy-on-Write代理可以让这个操作延迟，只有对象被用到的时候才被克隆。
保护(Protect or Access)代理：控制对一个对象的访问，可以给不同的用户提供不同级别的使用权限。
缓冲(Cache)代理：为某一个目标操作的结果提供临时的存储空间，以便多个客户端可以共享这些结果。
防火墙(Firewall)代理：保护目标不让恶意用户接近。
同步化(Synchronization)代理：使几个用户能够同时使用一个对象而没有冲突。
智能引用(Smart Reference)代理：当一个对象被引用时，提供一些额外的操作，如将此对象被调用的次数记录下来等。
6.6 动态代理
动态代理是一种较为高级的代理模式，它的典型应用就是Spring AOP。
在传统的代理模式中，客户端通过Proxy调用RealSubject类的request()方法，同时还在代理类中封装了其他方法(如preRequest()和postRequest())，可以处理一些其他问题。
如果按照这种方法使用代理模式，那么真实主题角色必须是事先已经存在的，并将其作为代理对象的内部成员属性。如果一个真实主题角色必须对应一个代理主题角色，这将导致系统中的类个数急剧增加，因此需要想办法减少系统中类的个数，此外，如何在事先不知道真实主题角色的情况下使用代理主题角色，这都是动态代理需要解决的问题。

以上就是6种结构型模式的内容。

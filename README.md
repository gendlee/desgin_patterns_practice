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

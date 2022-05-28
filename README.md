# desgin_patterns_practice
设计模式实践记录
# 0010 设计模式必知必会

# 一、创建型模式

创建型模式是对类的实例化的抽象，对类的创建和使用分离。主要包括以下6种，如下表所示。

| 模式名 | 实践中用名 | 重要程度（满分为5星） |
| --- | --- | --- |
| 工厂方法模式 | Factory Method | * * * * *（5星） |
| 抽象工厂模式 | Abstract Factory | * * * * *（5星） |
| 单例模式 | Singleton | * * * *（4星） |
| 简单工厂模式 | Simple Factory | * * * *（4星） |
| 原型模式 | Prototype | * * * （3星） |
| 建造者模式 | Builder | * * （2星） |

## 1、工厂方法模式

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

---

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

# 参考文献

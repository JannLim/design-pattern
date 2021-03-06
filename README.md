[TOC]
# 什么是设计模式
设计模式（Design pattern）是一套反复被使用、多数人知晓的、经过分类编目的、代码设计经验的总结。
# 为什么使用设计模式
实用设计模式是为了可重用代码、让代码更容易被他人理解、保证代码的可靠性。
# 设计模式分类
**创建型模式** 对象实例化的模式，用于解耦对象实例化过程  
**结构型模式** 把类或对象结合在一起，形成一个更大的结构  
**行为型模式** 类和对象如何交互、以及划分责任和算法  
![设计模式][design_pattern]
# 设计模式介绍及代码描述
## 创建型模式
### 单例模式  
确保一个类只有一个实例，并且提供全局的访问点  
**特点：** 1.只有一个实例 2.自我实例化 3.全局提供访问点  
**用途：** 当系统中只需要一个实例对象或者系统中只允许一个公共访问点时，使用单例模式  
**优点：** 节约资源、提高效率  
**缺点：** 单例类的职责过重，违背了“单一职责的原则”，没有抽象类，拓展困难  
```java
/**
 *单例模式 - 懒汉式
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy(){}

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

}

/**
 *单例模式 - 饿汉式
 */
public class SingletonHungry {

    private static final SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){}

    public static SingletonHungry getInstance() {
        return instance;
    }

}
```

### 简单工厂模式
一个工厂，根据传入的参数决定创建出哪一种类的产品实例  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象产品|具体产品的父类|描述产品的公共接口|  
具体产品|抽象产品的子类，工厂创建的目标类|描述生产的具体产品|  
工厂|被外界调用|根据传入不同的的参数，而创建不同的具体产品|  
**用途：** 工厂负责创建的对象较少，客户只知道传入参数，不在意创建对象的逻辑时  
**优点：** 1.将创建实例与使用实例分开，使用者无需关注怎样创建，实现解耦 2.将实例对象放入工厂中进行，易于维护，更符合面向对象和面向接口编程  
**缺点：** 1.集中了所有实例的创建逻辑，一旦工厂不能工作，影响整个系统 2.违背“开闭原则”，一旦添加产品，就必须修改工厂 3.使用静态方法，不能被继承和重写，导致工厂角色无法成给予集成的等级结构  
```java
public class SimpleFactory {
    public static void main(String[] args) {
        Factory.getProduct("A").create();
        Factory.getProduct("B").create();
    }
}

/**
 * 抽象产品
 */
abstract class Product {
    public abstract void create();
}

/**
 * 具体产品A
 */
class ProductA extends Product {
    @Override
    public void create() {
        System.out.println("创建产品A");
    }
}

/**
 * 具体产品B
 */
class ProductB extends Product {
    @Override
    public void create() {
        System.out.println("创建产品B");
    }
}

/**
 * 工厂，静态方法创建产品
 */
class Factory {
    public static Product getProduct(String name) {
        switch (name) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }
}
```
### 工厂方法模式
定义一个创建对象的接口，但子类决定要实例化的类是哪一个，即工厂方法模式让实例化推迟到子类  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|
抽象产品|具体产品的父类|描述具体产品的公共接口|  
具体产品|抽象产品的子类，工厂类创建的目标|描述具体生产的产品|  
抽象工厂|具体工厂的父类|描述具体工厂的公共接口|  
具体工厂|抽象工厂的子类，被外界调用|描述具体工厂，实现工厂公共接口创建产品|  
**用途：** 1.客户端不知道产品类名，只知道对应工厂，不关心生产过程 2.新加产品时，不修改原有代码，只需要新加工厂即可  
**优点：** 很好的符合“开闭原则”，“单一职责原则”，形成可以继承的结构  
**缺点：** 添加新产品时，需要添加相应的工厂，类的个数成对增加，增加系统复杂度和额外开销
```java
public class FactoryMethod {
    public static void main(String[] args) {
        MethodFactory factoryA = new MethodProductFactoryA();
        factoryA.getProduct().create();
        MethodFactory factoryB = new MethodProductFactoryB();
        factoryB.getProduct().create();
    }
}

/**
 * 抽象产品
 */
abstract class MethodProduct {
    public abstract void create();
}

/**
 * 具体产品A
 */
class MethodProductA extends MethodProduct {
    @Override
    public void create() {
        System.out.println("创建产品A");
    }
}

/**
 * 具体产品B
 */
class MethodProductB extends MethodProduct {
    @Override
    public void create() {
        System.out.println("创建产品B");
    }
}

/**
 * 抽象工厂
 */
abstract class MethodFactory {
    public abstract MethodProduct getProduct();
}

/**
 * 具体工厂A
 */
class MethodProductFactoryA extends MethodFactory {
    @Override
    public MethodProduct getProduct() {
        return new MethodProductA();
    }
}

/**
 * 具体工厂B
 */
class MethodProductFactoryB extends MethodFactory {
    @Override
    public MethodProduct getProduct() {
        return new MethodProductB();
    }
}
```
### 抽象工厂模式
提供一个接口，创建一组相关的产品，不需要知道或者关心具体的产品是什么，这样可以从具体的产品中解耦  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象产品家族|抽象产品的父类|描述抽象产品的公共接口|  
抽象产品|具体产品的父类|描述具体产品的公共接口|  
具体产品|抽象产品的子类，工厂类创建的目标类|描述生产的具体产品|  
抽象工厂|具体工厂的父类|描述具体工厂的公共接口|  
具体工厂|抽象工厂的子类，被外界调用|描述具体工厂，实现抽象工厂方法创建产品|  
**用途：** 要求提供一个产品类的库，所有产品都可以在一个工厂中生产  
**优点：** 相较于工厂方法模式，抽象工厂模式工厂可以生产多种类型产品，但是工厂方法模式只能生产单一产品  
**缺点：** 新增产品行为比较复杂，每新增一个产品，就需要修改抽象工厂的接口，涉及到所有子类的改变  
```java
public class AbstractFactory {
    public static void main(String[] args) {
        AbsFactory factoryA = new AbsFactoryA();
        factoryA.getContainer().create();
        factoryA.getModule().create();
        AbsFactory factoryB = new AbsFactoryB();
        factoryB.getContainer().create();
        factoryB.getModule().create();
    }
}

/**
 * 抽象产品家族
 */
abstract class ProductFamily {
    public abstract void create();
}

/**
 * 产品容器
 */
abstract class Container extends ProductFamily {
    @Override
    public abstract void create();
}

/**
 * 产品模具
 */
abstract class Module extends ProductFamily {
    @Override
    public abstract void create();
}

/**
 * 容器A
 */
class ContainerA extends Container {
    @Override
    public void create() {
        System.out.println("容器A创建");
    }
}

/**
 * 模具A
 */
class ModuleA extends Module {
    @Override
    public void create() {
        System.out.println("模具A创建");
    }
}

/**
 * 容器B
 */
class ContainerB extends Container {
    @Override
    public void create() {
        System.out.println("容器B创建");
    }
}

/**
 * 模具B
 */
class ModuleB extends Module {
    @Override
    public void create() {
        System.out.println("模具B创建");
    }
}

/**
 * 抽象工厂
 */
abstract class AbsFactory {
    public abstract Container getContainer();
    public abstract Module getModule();
}

/**
 * 工厂A
 */
class AbsFactoryA extends AbsFactory {
    @Override
    public Container getContainer() {
        return new ContainerA();
    }

    @Override
    public Module getModule() {
        return new ModuleA();
    }
}

/**
 * 工厂B
 */
class AbsFactoryB extends AbsFactory {
    @Override
    public Container getContainer() {
        return new ContainerB();
    }

    @Override
    public Module getModule() {
        return new ModuleB();
    }
}
```
### 建造者模式
将一个复杂的对象构建与表示分离，使得同样的构建过程可以创建不同的表示  
**特点：** 流程固定（顺序不一定固定），建造目标对象会有所改变，也可以表示为代替多参数构造器  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
指挥者|指挥具体建造者，外部调用|调用适当的建造者来组件产品，类似于产品经理|  
抽象建造者|所有具体建造者的父类|描述具体建造者的所有公共接口，类似于项目经理|  
具体建造者|抽象建造者的子类，产品的具体实施着|实现抽象建造者的所有接口，类似于程序员|  
产品|建造的对象|较为复杂的对象，类似于客户想要的产品|  
**用途：** mybatis逆向生成的Example就使用了建造者模式，建造不同查询条件的sql语句，idea中创建get、set方法中的Builder也是使用了这种模式  
**优点：** 将创建产品的步骤与产品本身分离，即使有相同的创建步骤，也能有不同的结果；建造者之间相互独立，想要替换现有建造者，添加一个新的即可  
**缺点：** 产品内部发生改变，所有的建造者都需改变，只适用于具有相同步骤的产品，差异大则不适用  
```java
public class BuilderPattern {
    public static void main(String[] args) {
        Builder builderA = new BuilderA();
        Director directorA = new Director(builderA);
        System.out.println(directorA.build());
        Builder builderB = new BuilderB();
        Director directorB = new Director(builderB);
        System.out.println(directorB.build());
    }
}

/**
 * 房子，产品的最终产物，房子需要安装门，窗户，地板
 */
class House {

    private String door;
    private String window;
    private String floor;

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "House{" +
                "door='" + door + '\'' +
                ", window='" + window + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}

/**
 * 抽象建造者，会装门，会装窗，会装地板
 */
interface Builder {
    void installDoor();
    void installWindow();
    void installFloor();

    House getHouse();
}

/**
 * 具体建造者A
 */
class BuilderA implements Builder {
    private House house = new House();

    @Override
    public void installDoor() {
        System.out.println("建造者A == > 安装门");
        house.setDoor("门");
    }

    @Override
    public void installWindow() {
        System.out.println("建造者A == > 安装窗");
        house.setWindow("窗");
    }

    @Override
    public void installFloor() {
        System.out.println("建造者A == > 安装地板");
        house.setFloor("地板");
    }

    @Override
    public House getHouse() {
        return house;
    }
}

/**
 * 具体建造者A
 */
class BuilderB implements Builder {
    private House house = new House();

    @Override
    public void installDoor() {
        System.out.println("建造者B == > 安装门");
        house.setDoor("门");
    }

    @Override
    public void installWindow() {
        System.out.println("建造者B == > 安装窗");
        house.setWindow("窗");
    }

    @Override
    public void installFloor() {
        System.out.println("建造者B == > 安装地板");
        house.setFloor("地板");
    }

    @Override
    public House getHouse() {
        return house;
    }
}

/**
 * 指挥者
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public House build() {
        builder.installDoor();
        builder.installFloor();
        builder.installWindow();
        return builder.getHouse();
    }
}
```
### 原型模式
通过复制现有实例来创建新的实例  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象原型|具体原型的父类|声明克隆的方法|  
具体原型|抽象原型的子类，外部调用|实现抽象原型中声明的克隆方法，返回克隆的对象|  
客户类|让具体原型对象克隆自身而得到一个新的对象|克隆方法的调用者|
**注意：Java中，Object里面又clone()方法，可以将Java对象复制一份，使用时实现Cloneable接口即可，此时Object就是抽象原型，如果步实现Cloneable接口会报错CloneNotSupportedException**  
**用途：** 创建新对象成本较大，对象相似度较高  
**优点：** 简化对象创建过程，提高创建效率  
**缺点：** 需要为每一个类配备克隆方法，违背开闭原则；当对象存在多层嵌套时，每层对象都要支持深刻隆，实现起来较为麻烦  
```java
public class PrototypePattern {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.setName("实例1");
        System.out.println("实例 == > " + prototype.getName());
        Prototype prototype1 = prototype.clone();
        System.out.println("实例 == > " + prototype1.getName());
    }
}

/**
 * 可以复制的类，必须继承Cloneable，否则在复制时报错CloneNotSupportedException
 */
class Prototype implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Prototype clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("clone is not support");
        }
        return (Prototype) obj;
    }
}
```
## 结构型模式
### 适配器模式
将一个类的方法接口转换成客户需要的另一个接口，让两个不兼容的接口能够完成无缝对接，例如充电器的转接头  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
适配者|适配器的父类|提供已有的功能接口|
适配器|适配者的子类，同时也是目标抽象者的实现类|实现目标抽象者定义的接口|  
目标抽象者|适配器的父类，外部访问|描述定义客户需要的接口|  
**优点：** 有更好的复用性，目标类和适配者类解耦，拓展性好，符合开闭原则  
**缺点：** 过多的使用适配器会让系统显得凌乱，不易于整体把握  
  
**类适配器模式**  
```java
public class ClassAdapterPattern {
    public static void main(String[] args) {
        ClassTarget target = new ClassAdapter();
        target.request();
    }
}

/**
 * 目标类
 */
interface ClassTarget {
    void request();
}

/**
 * 适配者
 */
class ClassAdaptee {
    public void adapteeRequest(){
        System.out.println("我是适配者");
    }
}

/**
 * 适配器
 */
class ClassAdapter extends ClassAdaptee implements ClassTarget {
    @Override
    public void request() {
        super.adapteeRequest();
    }
}
```
**对象适配器模式**  
```java

public class ObjectAdapterPattern {
    public static void main(String[] args) {
        ObjectTarget target = new ObjectAdapter(new ObjectAdaptee());
        target.request();
    }
}

/**
 * 目标类
 */
interface ObjectTarget {
    void request();
}

/**
 * 适配者类
 */
class ObjectAdaptee {
    public void adapteeRequest() {
        System.out.println("我是适配者");
    }
}

/**
 * 适配器
 */
class ObjectAdapter implements ObjectTarget {

    private ObjectAdaptee adaptee;

    public ObjectAdapter(ObjectAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
```
### 桥接模式
将抽象部分和实现部分分离，是他们能够分别独立进行变化  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象类|扩充抽象类的父类，拥有实现类接口的引用，外部调用|描述某一类型事物的公共方法|    
扩充抽象类|抽象类的子类，外部调用|补充抽象类的额外公共方法|  
实现类接口|具体实现类的父类|描述具体实现事物的公共方法|  
具体实现类|实现类接口的子类|描述实现具体实现类事物的公公方法|  
**用途：** 抽象部分和实现部分都可以拓展；多维度的场景利用桥接模式来分离子类  
**优点：** 抽象类和实现分离，扩展能力强，实现细节对客户透明  
**缺点：** 增加系统对设计的理解难度，将聚合关联关系建立在抽象层，针对抽象层进行设计与开发  
```java
public class BridgePattern {
    public static void main(String[] args) {
        MediumCoffee coffeeA = new MediumCoffee();
        coffeeA.orderCoffee();
        BigCoffee coffeeB = new BigCoffee(new Creamer());
        coffeeB.orderCoffee();
        coffeeB.mixing();
        OversizedCoffee coffeeC = new OversizedCoffee(new Suger());
        coffeeC.orderCoffee();
        coffeeC.mixing();
    }
}

/**
 * 实现类接口
 */
interface CoffeeAdditives{
    void addSomething();
}

/**
 * 具体实现类接口A
 */
class Creamer implements CoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("+奶精");
    }
}

/**
 * 具体实现类接口B
 */
class Suger implements CoffeeAdditives {
    @Override
    public void addSomething() {
        System.out.println("+糖");
    }
}

/**
 * 抽象类接口
 */
abstract class Coffee {
    protected CoffeeAdditives additives;
    public Coffee(){}

    public Coffee(CoffeeAdditives additives) {
        this.additives = additives;
    }

    public abstract void orderCoffee();
}

/**
 * 扩充抽象类
 */
abstract class RefinedCoffee extends Coffee {
    public RefinedCoffee(){}

    public RefinedCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    public void mixing() {
        super.additives.addSomething();
    }
}

/**
 * 抽象类实现A，如果想要添加抽象扩展类，则实现类继承扩展类
 */
class MediumCoffee extends RefinedCoffee {
    public MediumCoffee(){}

    public MediumCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee() {
        System.out.println("中杯咖啡");
    }
}

/**
 * 抽象类实现B，如果想要添加抽象扩展类，则实现类继承扩展类
 */
class BigCoffee extends RefinedCoffee {
    public BigCoffee(){}

    public BigCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee() {
        System.out.println("大杯咖啡");
    }
}

/**
 * 抽象类实现C，如果想要添加抽象扩展类，则实现类继承扩展类
 */
class OversizedCoffee extends RefinedCoffee {
    public OversizedCoffee(){}

    public OversizedCoffee(CoffeeAdditives additives) {
        super(additives);
    }

    @Override
    public void orderCoffee() {
        System.out.println("超大杯咖啡");
    }
}
```
### 组合模式
也叫合成模式，用来描述部分与整体的关系，即将部分组装成整体，将对象组装成树形结构，使得用户对但个对象和组合对象的使用有一致性  
**模式组成：**  
组成（角色）|描述|  
:-|:-|  
抽象构件角色|定义参加组合对象的共有方法和属性|  
叶子构件|遍历的最小单位，下面再也没有分支|  
树枝构件|结合树枝节点和子叶节点形成树形结构|  
**用途：** 树形菜单，文件和文件夹管理  
**优点：** 高层模块调用简单，节点增加自由  
**缺点：** 树枝和树叶直接使用了实现类，不符合面向接口编程  
```java

public class CompositionPattern {

    public static void display(Composite root) {
        for (Component temp : root.getChildren()) {
            if (temp instanceof Leaf) {
                temp.getName();
            } else {
                temp.getName();
                display((Composite) temp);
            }
        }
    }

    public static void main(String[] args) {
        Composite root = new Composite();
        root.getName();
        Composite branch = new Composite();
        root.add(branch);
        Leaf leaf = new Leaf();
        branch.add(leaf);

        display(root);
    }
}

/**
 * 角色抽象
 */
abstract class Component {
    public abstract void getName();
}

/**
 * 叶子构建
 */
class Leaf extends Component {
    @Override
    public void getName() {
        System.out.println("我是叶子");
    }
}

/**
 * 树枝构建
 */
class Composite extends Component {
    private List<Component> components = new ArrayList<>();

    // 新增
    public void add(Component component) {
        components.add(component);
    }

    // 删除
    public void remove(Component component) {
        components.remove(component);
    }

    // 获得子类
    public List<Component> getChildren() {
        return components;
    }

    @Override
    public void getName() {
        System.out.println("我是树枝");
    }
}
```
### 装饰模式
在不改变愿有对象的基础上，给原有对象添加功能，是对继承关系的替代方案  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象角色|具体角色的父类|描述被装饰角色的公共属性方法|  
具体角色|抽象角色的子类或实现类|实现所有被装饰角色的公共方法|  
抽象装饰|具体装饰的父类|描述具体装饰的所有公共属性方法，包含抽象角色的饮用|  
具体装饰|抽象装饰的子类，外部调用|实现所有抽象装饰的公共方法|  
**优点：** 比继承更加灵活，可以动态的给一个对象添加功能，符合开闭原则，具体装饰者和具体被装饰者独立  
**缺点：** 会写更多的代码，创建更多的对象  
```java
public class WrapperPattern {
    public static void main(String[] args) {
        Person personA = new PersonA();
        Coat coatA = new CoatA(personA);
        coatA.getCloths();
        Pants pantsA = new Pants(personA);
        pantsA.getCloths();

        Person personB = new PersonB();
        CoatB coatB = new CoatB(personB);
        coatB.getCloths();
    }
}

/**
 * 抽象角色
 */
abstract class Person {
    // 获取穿着搭配
    public abstract String getCloths();
}

/**
 * 具体角色A
 */
class PersonA extends Person {
    @Override
    public String getCloths() {
        return "person A 的搭配 == > ";
    }
}

/**
 * 具体角色B
 */
class PersonB extends Person {
    @Override
    public String getCloths() {
        return "person B 的搭配 == > ";
    }
}

/**
 * 抽象装饰角色
 * 上装
 */
abstract class Coat {
    // 被装饰的对象
    protected Person person;

    public Coat(Person person) {
        this.person = person;
    }

    public abstract void getCloths();
}

/**
 * 具体的装饰角色
 */
class CoatA extends Coat {

    public CoatA(Person person) {
        super(person);
    }

    @Override
    public void getCloths() {
        System.out.println(person.getCloths() + "衬衫");
    }
}

/**
 * 具体的装饰角色
 */
class CoatB extends Coat {

    public CoatB(Person person) {
        super(person);
    }

    @Override
    public void getCloths() {
        System.out.println(person.getCloths() + "T恤");
    }
}

/**
 * 具体的装饰角色，可以不经过抽象装饰角色
 * 下装
 */
class Pants {
    // 被装饰的对象
    private Person person;

    public Pants(Person person) {
        this.person = person;
    }

    public void getCloths() {
        System.out.println(person.getCloths() + "普通裤子");
    }
}
```
### 外观模式
提供一个统一的接口，访问子系统的一群接口
**模式组成：**  
组成（角色）|作用|
:-|:-|  
外观角色|客户端调用他的方法，使得任务可以分发至子系统|  
子系统角色|实际完成业务的系统，子系统可以被外观角色调用，也可以被客户端直接调用，不知道外观角色的存在|  
**优点：** 降低客户类与子系统的耦合度，屏蔽子系统组件，简化接口  
**缺点：** 在不引入抽象类的情况下，新增子系统需要改变外观类或客户端的源代码，违背开闭原则  
```java
public class FacadePattern {
    public static void main(String[] args) {
        // 程序员的一天，起床吃饭，写代码，晚上睡觉
        SubSystemA subSystemA = new SubSystemA();
        SubSystemB subSystemB = new SubSystemB();
        SubSystemC subSystemC = new SubSystemC();
        subSystemA.eating();
        subSystemB.coding();
        subSystemC.sleeping();

        System.out.println("===== 以下为外观模式 =====");
        Facade facade = new Facade(new SubSystemA(), new SubSystemB(), new SubSystemC());
        facade.programmersDay();
    }
}

/**
 * 子系统A
 */
class SubSystemA {
    public void eating() {
        System.out.println("吃饭");
    }
}

/**
 * 子系统B
 */
class SubSystemB {
    public void coding() {
        System.out.println("写代码");
    }
}

/**
 * 子系统C
 */
class SubSystemC {
    public void sleeping() {
        System.out.println("睡觉");
    }
}

/**
 * 外观角色 - 门面
 */
class Facade {
    private SubSystemA subSystemA;
    private SubSystemB subSystemB;
    private SubSystemC subSystemC;

    public Facade(SubSystemA subSystemA, SubSystemB subSystemB, SubSystemC subSystemC) {
        this.subSystemA = subSystemA;
        this.subSystemB = subSystemB;
        this.subSystemC = subSystemC;
    }

    public void programmersDay() {
        subSystemA.eating();
        subSystemB.coding();
        subSystemC.sleeping();
    }
}
```
### 享元模式
通过共享技术来有效的支持大量细粒度对象，最常见的就是String的定义，会放在常量池中  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象享元类|具体享元类的父类|描述具体享元类的公共方法属性|  
具体享元类|抽象享元类的实现类|实现了已定的具体接口|  
享元工厂类|外部调用|维护相同的享元对象，保证对象被共享|  
**用途：** 用共享来避免拥有大量相同内容对象的开销  
**优点：** 大幅度降低内存中对象的数量  
**缺点：** 使得系统更加复杂  
```java
public class FlyweightPattern {
    public static void main(String[] args) {
        FlyweightFactory factoryA = FlyweightFactory.getInstance();
        FlyweightFactory factoryB = FlyweightFactory.getInstance();
        Flyweight flyweightA = factoryA.getOrder("三国演义");
        Flyweight flyweightB = factoryB.getOrder("三国演义");
        Flyweight flyweightC = factoryA.getOrder("水浒传");
        flyweightA.sell();
        System.out.println(flyweightA);
        flyweightB.sell();
        System.out.println(flyweightB);
        flyweightB.sell();
        System.out.println(flyweightC);
    }
}

/**
 * 抽象享元类
 */
interface Flyweight {
    void sell();
}

/**
 * 具体享元类
 */
class ConcreteFlyweight implements Flyweight {

    private String name;

    public ConcreteFlyweight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sell() {
        System.out.println("卖了一本书 == > " + name);
    }
}

/**
 * 享元工厂
 */
class FlyweightFactory {
    private Map<String, Flyweight> pools = new HashMap<>();
    private static FlyweightFactory instance = new FlyweightFactory();
    private FlyweightFactory(){}

    public static FlyweightFactory getInstance() {
        return instance;
    }

    public Flyweight getOrder(String name) {
        Flyweight flyweight;
        if (pools.containsKey(name)) {
            flyweight = pools.get(name);
        } else {
            flyweight = new ConcreteFlyweight(name);
            pools.put(name, flyweight);
        }
        return flyweight;
    }

}
```
### 代理模式
为其他对象提供一个代理，以便控制这个对象的访问，使得用户不能真正访问目标对象  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象对象|真实对象和代理对象的父类|用来声明真实对象和代理对象的公共方法|  
真实对象|抽象对象的子类|最终被执行方法的对象|  
代理对象|抽象对象的子类，外界调用|包含一个真实对象的引用，将外界的操作传递至真实对象，同时添加自己独特的方法|  
**用途：** AOP就是使用了动态代理进行实现的  
**优点：** 职责清晰，拓展性高，很好的保护了真是对象，同时代理对象还可以添加自己特有的功能  
**缺点：** 在客户和真是对象之间添加代理对象，造成系统的请求速度变慢；增加系统实现的复杂度  
**静态代理模式**  
```java
/**
 * @describe: 静态代理模式
 * 为其他对象提供一个代理，以便控制这个对象的访问，使得用户不能真正访问目标对象
 * Subject 抽象对象 声明真实对象和代理对象的公共特点
 * RealSubject 真实对象 最终引用的对象
 * Proxy 代理对象 包含对真实对象的引用
 * 静态代理代理对象继承或实现抽象对象，在编译时已经创建实际的代理class
 * @author: linjuanjuan
 * @date: 2020-02-16 22:25
 */
public class StaticProxyPattern {
    public static void main(String[] args) {
        StaticProxy proxy = new StaticProxy(new StaticRealSubject());
        proxy.buyMac();
    }
}

/**
 * 抽象对象
 */
interface StaticSubject {
    void buyMac();
}

/**
 * 真实对象
 */
class StaticRealSubject implements StaticSubject {
    @Override
    public void buyMac() {
        System.out.println("买Mac");
    }
}

/**
 * 代理对象
 */
class StaticProxy implements StaticSubject {
    private StaticSubject target;

    public StaticProxy(StaticSubject target) {
        this.target = target;
    }

    @Override
    public void buyMac() {
        target.buyMac();
        // 代理做的额外操作
        System.out.println("打包Mac，寄出");
    }
}
```
**动态代理**  
```java
/**
 * @describe: 动态代理模式
 * 为其他对象提供一个代理，以便控制这个对象的访问，使得用户不能真正访问目标对象
 * Subject 抽象对象 声明真实对象和代理对象的公共特点
 * RealSubject 真实对象 最终引用的对象
 * Proxy 代理对象 包含对真实对象的引用
 * 动态代理不继承或者抽象对象，利用jdk动态的在内存中创建对象，实现目标对象的代理功能，在编译时还没有实际的代理class类，
 * 在运行时动态生成，添加进jvm中的
 * @author: linjuanjuan
 * @date: 2020-02-16 22:47
 */
public class DynamicProxyPattern {
    public static void main(String[] args) {
        DynamicSubject subject = new DynamicRealSubject();
        DynamicSubject proxy = (DynamicSubject) new DynamicProxy(subject).getProxyInstance();
        proxy.buyMac();
    }
}

/**
 * 抽象对象
 */
interface DynamicSubject {
    void buyMac();
}

/**
 * 真实对象
 */
class DynamicRealSubject implements DynamicSubject {
    @Override
    public void buyMac() {
        System.out.println("买Mac");
    }
}

/**
 * 代理对象
 * 需要实现InvocationHandler
 */
class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理实例上处理方法调用并返回结果
        Object result = method.invoke(target, args);
        // 代理做的额外操作
        System.out.println("打包Mac，寄出");
        return result;
    }
}
```
## 行为型模式
### 访问者模式
在不改变数据结构的情况下，增加一组作用于对象元素的新功能。在开发中，常常会对同一个对象产生不同的处理，如果我们都分别作处理，那么当该对象发生改变时，将会导致曾经的处理方式也需要跟着进行改变。访问者模式对于解决这种问题提供了较好的方案，访问者模式就是为不同类型的元素提供多种访问操作方式，并且可以在不修改原有系统的情况下，增加新的访问方式。访问者模式适用于数据结构较为稳定的，操作易于变化的。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象访问者|具体访问者的父类|为每一个具体元素声明操作|  
具体访问者|抽象访问者的子类|实现抽象访问者声明的操作|  
抽象元素|具体元素的父类|定义描述元素的公共属性及方法，同时定义accept操作，以访问者为入参|  
具体元素|抽象元素的子类|实现accept方法|  
对象结构|外部访问，包含元素的对象集合|用于存放元素对象，且提供便利的内部元素方法|  
**用途：** 对象结构中，对象对应的类很少改变，但经常要在结构上定义新的操作。  
**优点：** 新增访问操作变得简单方便，符合开闭原则；将有关元素对象的访问操作集中到一个访问者中，而不是分散到一个个元素类中，使类的职责更加清晰，符合单一职责原则。  
**缺点：** 增加新的元素困难，需要相应增加每个访问者的相关操作，违背了开闭原则；元素对象需要暴漏一些自己的内部操作，否则无法提供访问者访问，破坏了封装性。  
```java
public class VisitorPattern {

    public static void main(String[] args) {
        // 医生开药单
        Prescriptions prescriptions = new Prescriptions();
        prescriptions.add(new MedicineA("阿莫西林", BigDecimal.valueOf(15)));
        prescriptions.add(new MedicineB("999感冒灵",BigDecimal.valueOf(18)));

        // 付钱
        Cashier cashier = new Cashier("收银员1");
        prescriptions.accept(cashier);

        // 拿药
        Pharmacist pharmacist = new Pharmacist("药师1");
        prescriptions.accept(pharmacist);
    }

}

/**
 * 药品父类 - 抽象元素
 * 定义描述元素的公共属性及方法，同时定义accept操作，以访问者为入参
 */
abstract class Medicine {
    protected String name;
    protected BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract void accept(Visitor visitor);
}

/**
 * 药品 - 具体元素
 */
class MedicineA extends Medicine {

    public MedicineA(){}

    public MedicineA(String name, BigDecimal price) {
        super.name = name;
        super.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}

/**
 * 药品 - 具体元素
 */
class MedicineB extends Medicine {

    public MedicineB(){}

    public MedicineB(String name, BigDecimal price) {
        super.name = name;
        super.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}

/**
 * 抽象访问者
 * 为每一个具体元素声明操作，可以利用重载，也可以不用，具体的被调用在具体元素中，以后添加元素只需要修改访问者即可
 */
abstract class Visitor {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void visitor(MedicineA medicine);

    public abstract void visitor(MedicineB medicine);
}

/**
 * 收银员 - 具体访问者
 */
class Cashier extends Visitor {

    public Cashier(){}

    public Cashier(String name){
        super.name = name;
    }

    @Override
    public void visitor(MedicineA medicine) {
        System.out.println("收银员：" + name + " 给药品：" + medicine.getName() + " 收费：" + medicine.getPrice());
    }

    @Override
    public void visitor(MedicineB medicine) {
        System.out.println("收银员：" + name + " 给药品：" + medicine.getName() + " 收费：" + medicine.getPrice());
    }
}

/**
 * 药师 - 具体访问者
 */
class Pharmacist extends Visitor {

    public Pharmacist(){}

    public Pharmacist(String name){
        super.name = name;
    }

    @Override
    public void visitor(MedicineA medicine) {
        System.out.println("药师：" + name + " 取药：" + medicine.getName());
    }

    @Override
    public void visitor(MedicineB medicine) {
        System.out.println("药师：" + name + " 取药：" + medicine.getName());
    }
}

/**
 * 药单 - 对象结构
 */
class Prescriptions {
    private List<Medicine> medicines;

    public Prescriptions(){
        medicines = new ArrayList<>();
    }

    public void add(Medicine medicine) {
        medicines.add(medicine);
    }

    public void remove(Medicine medicine) {
        medicines.remove(medicine);
    }

    public void accept(Visitor visitor) {
        Iterator<Medicine> iterator = medicines.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }
}
```
### 模板模式  
在一个方法中定义算法骨架，将一些步骤延迟到子类中，模板方法可以在不改变算法结构的情况下，重新定义算法中的某些步骤。  
模板方法就是基于继承的代码复用技术，在模板模式中，我们可以将相同的方法放到父类中，不同的方法放到子类中。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象类|具体子类的父类|定义模板骨架，提取相同的方法|  
具体子类|实现了抽象类|重写父类中定义的需要被重写的方法|  
**用途：** 实现一个算法的不变部分，将可变部分留给子类实现；通过子类决定父类中的某个步骤是否执行。  
**优点：** 封装不变的部分，拓展可变的部分；提取公共代码，便于维护；行为由父类控制，子类负责实现。  
**缺点：** 每一个不同的实现都需要一个子类来实现，使得系统更加庞大。  
```java
public class TemplatePattern {

    public static void main(String[] args) {
        // 清朝时蔬
        Vegetables vegetables = new Vegetables();
        vegetables.cook();
        System.out.println(" ======================== ");
        // 水煮肉片
        PoachedMeat poachedMeat = new PoachedMeat();
        poachedMeat.cook();
    }

}

/**
 * 抽象父类
 */
abstract class Cook {

    // 准备原材料
    protected abstract void prepareRawMaterials();

    // 倒油加热
    protected void addOil() {
        System.out.println("开火，倒油，加热");
    }

    // 加入材料进行翻炒
    protected void stirFry() {
        System.out.println("炒啊炒");
    }

    // 加入调料
    protected abstract void addCondiment();

    // 关火出锅
    protected void finish() {
        System.out.println("炒啊炒，装盘出锅");
    }

    // 炒菜
    public void cook() {
        prepareRawMaterials();
        addOil();
        stirFry();
        addCondiment();
        finish();
    }

}

/**
 * 清炒时蔬 - 具体子类
 */
class Vegetables extends Cook {

    @Override
    public void prepareRawMaterials() {
        System.out.println("准备青菜，洗净装盘");
    }

    @Override
    public void addCondiment() {
        System.out.println("顺序加入糖，盐，老抽，醋");
    }
}

/**
 * 水煮肉片 - 具体子类
 */
class PoachedMeat extends Cook {

    @Override
    public void prepareRawMaterials() {
        System.out.println("准备猪肉切片，准备青菜洗净，豆芽洗净");
    }

    @Override
    public void addCondiment() {
        System.out.println("加入火锅底料，花椒，糖，盐，老抽，醋");
    }
}
```
### 策略模式
定义一组算法，将其分别封装起来，使他们可以相互替换，让算法可以在不影响客户端的情况下发生变化。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象策略类|具体策略类的父类|定义策略的公共属性|  
具体策略类|抽象策略类的子类|继承抽象策略类，同时实现具体的策略内容|  
环境类|包含一个抽象策略类的引用|连接上下文信息，给出策略结果|  
**用途：** 一个系统需要动态的在几种算法中选择一种；如果在一个系统里面有很多类，他们的区别仅在于他们的行为，那么利用策略模式可以让一个对象在许多行为中动态的选择一种；不希望客户端知道复杂的，与算法相关的数据结构，在策略类中封装算法相关的数据结构，提高算法的保密性与安全性。  
**优点：** 策略之间可以自由切换；新增加一个策略只需要增加策略类即可，符合开闭原则；避免多重条件语句(if-else)。  
**缺点：** 客户端必须知道所有的策略类，并自行决定要用哪一种；每个策略都是单独的类，如果备选数量很多的话，就会出现很多的类。  
```java
public class StrategyPattern {

    public static void main(String[] args) {
        BigDecimal amt = BigDecimal.valueOf(100);
        // 普通客户
        Settlement normal = new Settlement(new NormalCustomer());
        System.out.println("支付金额 == > " + normal.calPrice(amt));
        // vip
        Settlement vip = new Settlement(new VipCustomer());
        System.out.println("支付金额 == > " + vip.calPrice(amt));
        // vvip
        Settlement vvip = new Settlement(new VvipCustomer());
        System.out.println("支付金额 == > " + vvip.calPrice(amt));
    }

}

/**
 * 客户类型 - 抽象策略类
 */
interface CustomerType {

    // 计算最终价格
    BigDecimal calPrice(BigDecimal amt);

}

/**
 * 普通客户 - 具体策略类
 */
class NormalCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 普通客户");
        return amt;
    }
}

/**
 * vip - 具体策略类
 */
class VipCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 VIP");
        return amt.multiply(BigDecimal.valueOf(0.9));
    }
}

/**
 * vvip - 具体策略类
 */
class VvipCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 VVIP");
        return amt.multiply(BigDecimal.valueOf(0.85));
    }
}

/**
 * 结算 - 环境类
 */
class Settlement {

    private CustomerType customerType;

    public Settlement(CustomerType customerType) {
        this.customerType = customerType;
    }

    // 结算
    public BigDecimal calPrice(BigDecimal amt) {
        return customerType.calPrice(amt);
    }

}
```
### 状态模式
在很多情况下，对象的行为依赖于它的一个或者多个变化的属性，这些可变的属性被称之为状态，也就是行为依赖状态，当该对象因为外部的互动导致状态发生变化，那么它的行为也会发生相应的变化。对我们来说，对象的行为是被状态控制的，什么状态要做出什么行为，这就是状态模式。状态模式就是允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象状态|具体状态的父类|定义对象的行为|  
具体状态|抽象状态的子类|实现具体的行为|  
环境角色|包含一个抽象状态的引用，外部调用|定义客户感兴趣的接口，用以控制对象的行为|  
**用途：** 行为随状态改变而改变的场景；if-else语句的替代者。  
**优点：** 封装了转换规则；将所有与某个状态有关的行为全部放在一个类中，并且可以方便的增加新的状态，只需要改变对象的状态就可以改变对象的行为。  
**缺点：** 增加系统中类的个数，使用不当容易导致程序结构和代码的混乱；增加新的状态需要修改负责状态转换的相关代码。  
```java
public class StatePattern {

    public static void main(String[] args) {
        // 正常客户
        CustomerOperate normal = new CustomerOperate(new NormalState());
        normal.saveMoney(BigDecimal.valueOf(100));
        normal.withdrawMoney(BigDecimal.valueOf(50));
        normal.withdrawMoney(BigDecimal.valueOf(100));
        System.out.println(" =========================== ");
        // 冻结客户
        CustomerOperate freeze = new CustomerOperate(new FreezeState());
        freeze.saveMoney(BigDecimal.valueOf(100));
        freeze.withdrawMoney(BigDecimal.valueOf(50));
        System.out.println(" =========================== ");
        // 注销客户
        CustomerOperate logout = new CustomerOperate(new LogoutState());
        logout.saveMoney(BigDecimal.valueOf(100));
        logout.withdrawMoney(BigDecimal.valueOf(50));
    }

}

/**
 * 账户状态 - 抽象状态
 */
interface AccountState {

    // 存钱
    void saveMoney(BigDecimal amt);

    // 取钱
    void withdrawMoney(BigDecimal amt);

}

/**
 * 正常状态 - 具体状态
 */
class NormalState implements AccountState {

    private BigDecimal balance;

    public NormalState() {
        balance = BigDecimal.ZERO;
    }

    @Override
    public void saveMoney(BigDecimal amt) {
        balance = amt.add(balance);
        System.out.println("账户状态正常，成功存入 == > " + amt);
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        if (balance.compareTo(amt) > 0) {
            System.out.println("账户状态正常，成功取出 == > " + amt);
        } else {
            System.out.println("账户状态正常，账户余额不足");
        }
    }
}

/**
 * 账户冻结 - 具体状态
 */
class FreezeState implements AccountState {

    @Override
    public void saveMoney(BigDecimal amt) {
        System.out.println("您的账户已被冻结");
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        System.out.println("您的账户已被冻结");
    }
}

/**
 * 注销状态 - 具体状态
 */
class LogoutState implements AccountState {

    @Override
    public void saveMoney(BigDecimal amt) {
        System.out.println("您的账户已被注销");
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        System.out.println("您的账户已被注销");
    }
}

/**
 * 客户操作 - 环境类
 */
class CustomerOperate {

    private AccountState accountState;

    public CustomerOperate(AccountState accountState) {
        this.accountState = accountState;
    }

    // 存钱
    public void saveMoney(BigDecimal amt) {
        accountState.saveMoney(amt);
    }

    // 取钱
    public void withdrawMoney(BigDecimal amt) {
        accountState.withdrawMoney(amt);
    }

}
```
### 观察者模式
描述的是一对多的关系（一个目标对象，多个观察者），当目标对象状态发生改变时，所有的观察者都会收到通知。观察者之间没有相互联系，可以根据需要增加或者删除。  
观察者模式就是在特定的时刻，目标对象「目标对象发送通知」干特定的事情「观察者收到通知并且处理自己相应的事情」。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
目标（抽象被观察者）|具体目标的父类|定义具体目标的公共行为，比如增加或者删除观察者|  
具体目标（真实被观察者）|目标的子类，拥有抽象观察者的属性列表|实现了目标的公共行为，同时维护自己的观察者列表，以便于发送通知|  
抽象观察者|观察者的父类|定义观察者收到通知后要进行的公共行为|  
具体观察者|抽象观察者的子类|实现自己收到目标通知后真正要干的事情|  
**用途：** 关联行为场景，注意行为是可拆分的，而不是组合关系；事件多级触发场景；跨系统的消息交换场景，如消息队列，事件总线的处理机制。  
**优点：** 解除耦合，让依赖的双方都依赖于抽象，从而使得各自的变换都不会影响另一边。  
**缺点：** 在应用观察者模式时需要考虑一下开发效率和运行效率等问题，程序包含一个被观察者和多个观察者，开发调试等会变得比较复杂，同时通知时顺序执行，如果一个观察者卡顿，会影响整体的执行效率，在这种情况下，一般会采用异步实现。  
```java
public class ObserverPattern {

    public static void main(String[] args) {
        PublicAccount_A accountA = new PublicAccount_A();
        Customer customer = new Customer();
        Company company = new Company();
        accountA.subscribe(customer);
        accountA.subscribe(company);
        accountA.publish();
        System.out.println(" ============================= ");
        accountA.unsubscribe(customer);
        accountA.publish();
    }

}

/**
 * 微信公众号 - 抽象目标
 */
interface WechatPublicAccount {

    // 订阅
    void subscribe(WechatCustomer customer);

    // 取消订阅
    void unsubscribe(WechatCustomer customer);

    // 发布文章
    void publish();

}

/**
 * 微信用户 - 抽象观察者
 */
interface WechatCustomer {

    // 接收通知
    void handel();

}

/**
 * 微信公众号
 */
class PublicAccount_A implements WechatPublicAccount {

    private List<WechatCustomer> customers = new ArrayList<>();

    @Override
    public void subscribe(WechatCustomer customer) {
        customers.add(customer);
    }

    @Override
    public void unsubscribe(WechatCustomer customer) {
        customers.remove(customer);
    }

    @Override
    public void publish() {
        customers.forEach(temp -> temp.handel());
    }
}

/**
 * 客户 - 具体观察者
 */
class Customer implements WechatCustomer {

    @Override
    public void handel() {
        System.out.println("普通用户点击查看文章");
    }
}

/**
 * 企业客户
 */
class Company implements WechatCustomer {

    @Override
    public void handel() {
        System.out.println("企业用户点击查看文章");
    }
}
```
### 备忘录模式
备忘录模式又叫快照模式，用于在不破坏封装的前提条件下，捕获一个对象的内部状态，并且在该对象之外保存这个状态，这样可以在以后将这个对象恢复到原先保存的状态。
类似于游戏的存档，备忘录模式给软件提供了一种后悔药机制，可以使系统恢复到某一特定的历史状态。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
发起人(Originator)|外部访问，真实需要被保存状态的对象|可以创建备忘录，用以保存当前状态，也可以恢复当前对象至某个状态|  
负责人(Caretaker)|包含一个备忘录属性|负责传递备忘录信息，不能直接访问备忘录|  
备忘录(Memento)||存储发起人的内部状态，有两个等效接口。**窄接口：** 除发起人以外的任何人看到的都是窄接口，只允许传递对象，无法访问内部属性。**宽接口：** 发起人看到的是宽接口，允许发起人读取所有数据，以便恢复发起人的内部状态|  
**用途：** 需要保存某个对象在某些时刻的一些状态；如果用一个接口来让其他对象得到这些状态，会暴露对象的实现细节和破坏对象的封装性，一个对象不希望外部直接访问他的内部状态，可以通过负责人间接访问。  
**优点：** 给用户提供了一种恢复状态的机制，可以方便的恢复到某个历史状态；实现了信息封装，使用户不需要关心状态的保存细节。  
**缺点：** 消耗资源，如果类的成员变量过多，势必会占用较大的资源，并且每一次保存都会消耗内存。  
**白箱模式**  
```java
public class MementoWhitePattern {

    public static void main(String[] args) {
        // 初始化发起人对象
        WhiteOriginator old = new WhiteOriginator("初始化");
        System.out.println("初始化的对象 ==> " + old.getState());
        // 交给负责人存档
        WhiteCaretaker caretaker = new WhiteCaretaker();
        caretaker.saveMemento(old.saveMemento());
        old.setState("修改");
        System.out.println("修改后的对象状态 ==> " + old.getState());
        // 恢复存档
        old.restoreMemento(caretaker.getMemento());
        System.out.println("恢复存档的对象 ==> " + old.getState());
    }

}

/**
 * 备忘录 - 存储状态的对象，状态是公共方法，所以可以被任何对象所访问
 */
class WhiteMemento {
    private String state;

    public WhiteMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 发起人 - 需要被存储状态的对象
 */
class WhiteOriginator {
    private String state;

    public WhiteOriginator(String state) {
        this.state = state;
    }

    // 创建存档
    public WhiteMemento saveMemento() {
        return new WhiteMemento(state);
    }

    // 恢复存档
    public void restoreMemento(WhiteMemento memento) {
        this.state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 负责人 - 仅仅负责传递备忘录，由于备忘录中存储的属性具有公共的get、set方法，所以可以被负责人所访问，访问不访问全靠开发人员自觉
 */
class WhiteCaretaker {

    private WhiteMemento memento;

    public void saveMemento(WhiteMemento memento) {
        this.memento = memento;
    }

    public WhiteMemento getMemento() {
        System.out.println("我是负责人，我知道备忘录中存储的信息 ==> " + memento.getState());
        return memento;
    }

}
```
**黑箱模式**  
```java
public class MementoBlackPattern {

    public static void main(String[] args) {
        BlackOriginator originator = new BlackOriginator("初始化");
        System.out.println("发起人状态 ==> " + originator.getState());
        // 存档
        BlackCaretaker caretaker = new BlackCaretaker();
        caretaker.saveMemento(originator.saveMemento());
        originator.setState("修改");
        System.out.println("发起人状态 ==> " + originator.getState());
        // 恢复存档
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("发起人状态 ==> " + originator.getState());
    }

}

/**
 * 备忘录 - 对外开放的备忘录父类，不具备任何属性，保证外部对象访问时，只能拿到窄接口
 */
interface Memento {

}

/**
 * 发起人 - 拥有一个私有的备忘录类，继承了备忘录父类，这样就能保证备忘录仅为自己和发起人提供宽接口
 */
class BlackOriginator {

    private String state;

    public BlackOriginator(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 存档
    public Memento saveMemento() {
        return new BlackMemento(state);
    }

    // 恢复存档
    public void restoreMemento(Memento memento) {
        this.state = ((BlackMemento) memento).getState();
    }

    // 提供窄接口的备忘录
    private class BlackMemento implements Memento {

        private String state;

        public BlackMemento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}

/**
 * 负责人 - 拥有一个备忘录父类作为属性，但是由于父类中只提供了窄接口，所以什么都看不到
 */
class BlackCaretaker {
    private Memento memento;

    public Memento getMemento() {
        return this.memento;
    }

    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}
```
### 中介者模式
用一个中介者对象封装一系列的对象交互，中介者使各对象不需要显示的相互作用，从而使得解耦合，而且可以独立的改变他们之间的交互。  
在中介者模式中，使用中介者对象来封装对象之间的关系，各个对象不需要知道具体信息，通过中介者对象就可以实现相互通信。它减少了对象之间的相互关系，提高了系统之间的可复用性，简化了系统的结构。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象同事类|具体同事类的父类|定义同事类的公共属性及方法|  
具体同事类|抽象同事类的子类|实现抽象同事类定义的方法，拥有一个中介者作为属性或者实现方法中有中介者作为入参|  
抽象中介者|具体中介者的父类|实现了同事对象到中介者之间的接口，定义具体中介者的公共属性|  
具体中介者|抽象中介者的子类|实现了抽象中介者中定义的方法，每个同事类只需要知道自己的行为，但是需要认识中介者（即拥有中介者作为入参的方法）|
**用途：** 系统中对象之间存在比较复杂的引用关系，导致他们之间的依赖关系结构混乱而且难以复用该对象；想通过一个中间类来封装多个类中的行为，而且又不想生成太多的子类。  
**优点：** 简化了对象之间的关系，将系统的各个对象之间的关系进行封装，将各个同事类进行解耦，降低系统的耦合度；减少子类的生成；减少各个同事类之间的设计与实现。  
**缺点：** 由于中介者对象封装了系统中对象之间的相互关系，导致其变得非常复杂，使得系统维护比较困难。  
```java
public class MediatorPattern {

    public static void main(String[] args) {
        // 创建一个租客和房东都认识的中介
        MediatorStructure structure = new MediatorStructure();
        // 租客
        Tenant tenant = new Tenant("张三", structure);
        // 房东
        LandLord landLord = new LandLord("王武", structure);
        // 中介建立房东与租客的连接
        structure.setTenant(tenant);
        structure.setLandLord(landLord);

        // 租客询问房租
        tenant.contact("您好，请问您的四室一厅的房子一个月多少钱");
        // 房东回复
        landLord.contact("一个月6500元");
    }

}

/**
 * 抽象中介者
 */
abstract class Mediator {

    public abstract void connect(String message, Colleague colleague);

}

/**
 * 中介
 */
class MediatorStructure extends Mediator {

    private Tenant tenant;
    private LandLord landLord;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public LandLord getLandLord() {
        return landLord;
    }

    public void setLandLord(LandLord landLord) {
        this.landLord = landLord;
    }

    @Override
    public void connect(String message, Colleague colleague) {
        // 租客发送信息，则房东可以得到信息，反之则反
        if (colleague instanceof Tenant) {
            System.out.println("租客" + colleague.getName() + "说：" + message);
            landLord.getMessage(message);
        } else {
            System.out.println("房东" + colleague.getName() + "说：" + message);
            tenant.getMessage(message);
        }
    }
}

/**
 * 抽象同事类
 */
abstract class Colleague {

    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}

/**
 * 租客 - 具体同事
 */
class Tenant extends Colleague {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 联系
    public void contact(String message) {
        mediator.connect(message, this);
    }

    // 获取信息
    public void getMessage(String message) {
        System.out.println("租客" + name + "得到的信息：" + message);
    }
}

/**
 * 房东 - 具体同事
 */
class LandLord extends Colleague {

    public LandLord(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 联系
    public void contact(String message) {
        mediator.connect(message, this);
    }

    // 获取信息
    public void getMessage(String message) {
        System.out.println("房东" + name + "得到的信息：" + message);
    }

}
```
### 迭代器模式
提供一种方法顺序访问聚合对象（数组、集合等）中的各个元素，而不暴露其内部表示。迭代器就是将迭代元素的责任交给迭代器，而不是聚合对象，我们甚至不需要知道该聚合对象的内部结构就可以实现对聚合对象的迭代。  
java中定义了Iterator，用于遍历Collection，每个Collection的子类都实现了创建迭代器，利用迭代器去遍历。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象迭代器|具体迭代器的父类|定义实现迭代器的最小方法集，比如需要hasNext()、next()方法等|  
具体迭代器|抽象迭代器的子类|根据聚合的类型，实现抽象迭代器定义的方法集|  
抽象聚合类|具体聚合类的父类|定义聚合类的公共方法，同时提供一个创建迭代器的方法，比如add()、remove()方法等|  
具体聚合类|抽象聚合类的子类|实现抽象聚合类定义的公共方法|  
**用途：** 访问一个聚合对象的内容，同时无需暴露它的内部结构；需要为聚合对象提供多种遍历方式；为遍历不同的聚合结构提供一种统一的接口。  
**优点：** 支持以不同的方式遍历一个聚合对象；迭代器简化了聚合类；在同一个聚合上可以有多个遍历；在迭代器模式中，增加新的聚合类和迭代器都很方便，无需修改原有代码。  
**缺点：** 由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要增加对应的迭代器，类的个数成对增加，在一定程度上增加了系统的复杂度。  
```java
public class IteratorPattern {

    public static void printMenu(MyIterator iterator) {
        if (iterator == null) {
            return;
        }
        while (iterator.hasNext()) {
            Food food = iterator.next();
            System.out.println("名称：" + food.getName() + "，价格：" + food.getPrice());
        }
    }

    public static void main(String[] args) {
        Breakfast breakfast = new Breakfast();
        printMenu(breakfast.iterator());
        Lunch lunch = new Lunch();
        printMenu(lunch.iterator());
        System.out.println(" =========== 添加早餐 =========== ");
        breakfast.add(new Food("豆浆", 3.0));
        printMenu(breakfast.iterator());
        printMenu(lunch.iterator());
    }

}

/**
 * 抽象迭代器，定义迭代器的公共方法
 */
interface MyIterator {

    // 是否有下一个
    boolean hasNext();
    // 获取下一个值
    Food next();

}

/**
 * 菜单 - 抽象聚合类
 */
abstract class Menu {

    // 创建迭代器
    public abstract MyIterator iterator();

}

/**
 * 食物
 */
class Food {
    private String name;
    private Double price;

    public Food(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * 早餐店
 */
class Breakfast extends Menu {

    private ArrayList<Food> foods = new ArrayList<>();

    public Breakfast() {
        foods.add(new Food("红薯粉", 5.0));
        foods.add(new Food("包子", 2.5));
    }

    void add(Food food) {
        foods.add(food);
    }

    void remove(Food food) {
        int k = -1;
        for (int i = 0; i < foods.size(); i++) {
            if (food.getName().equals(foods.get(i).getName()) && food.getPrice() == foods.get(i).getPrice()) {
                k = i;
                break;
            }
        }
        if (k == -1) {
            System.out.println("早餐不存在");
        } else {
            foods.remove(k);
        }
    }

    @Override
    public MyIterator iterator() {
        return new BreakfastIterator(foods);
    }
}

/**
 * 早餐店迭代器 - 具体迭代器
 */
class BreakfastIterator implements MyIterator {

    private List<Food> foods;
    private Integer i;

    public BreakfastIterator(List<Food> foods) {
        this.foods = foods;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        if (i < foods.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Food next() {
        Food food = foods.get(i);
        i++;
        return food;
    }
}

/**
 * 午餐店
 */
class Lunch extends Menu {

    private final static Integer MAX = 6;
    private Food[] foods = new Food[MAX];

    public Lunch() {
        foods[0] = new Food("红烧肉", 8.0);
        foods[1] = new Food("鱼香肉丝", 10.0);
        foods[2] = new Food("水煮鱼", 15.0);
    }

    @Override
    public MyIterator iterator() {
        return new LunchIterator(foods);
    }
}

/**
 * 午餐迭代器 - 具体迭代器
 */
class LunchIterator implements MyIterator {

    private Food[] foods;
    private Integer i;

    public LunchIterator(Food[] foods) {
        this.foods = foods;
        i = 0;
    }

    @Override
    public boolean hasNext() {
        if (i >= foods.length) {
            return false;
        }
        Food food = foods[i];
        if (food == null) {
            return false;
        }
        return true;
    }

    @Override
    public Food next() {
        Food food = foods[i];
        i++;
        return food;
    }
}
```
### 解释器模式
定立一种语言的文法，并且建立解释器来解释该语言中的句子。解释器模式描述了如何构成一个简单的语言解释器，主要应用在使用面向对象语言开发的编译器中。它描述了如何为一个简单的语言定义一个文法，如何在该语言中表示一个句子，以及如何解释这些句子。  
**模式组成：**  
组成（角色）|关系|作用|  
:-|:-|:-|  
抽象表达式|终结符表达式，非终结符表达式的父类|定义表达式的解释操作|  
终结符表达式|抽象表达式的子类|实现文法中元素相关的解释操作，通常一个解释器只有一个终结符表达式|  
非终结符表达式|抽象表达式的子类|实现文法中运算符或者其他关键字的解释，非终结符表达式根据逻辑的复杂程度而增加，原则上每个文法都对应一个非终结符表达式|  
环境角色|存放终结符的具体值|存放文法中终结符的具体值|  
**用途：** 一些重复出现的问题可以用一种简单的语言来进行表达；可以将一个需要解释器执行的语言中的句子表示为一个抽象语法树。  
**优点：** 易于改变和拓展文法；实现文法较为容易；增加新的表达式解释器较为方便。  
**缺点：** 对于复杂的文法难以维护；执行效率较低。  
```java
public class InterpreterPattern {

    public static void main(String[] args) {
        String exp = "one add two";

        Context context = new Context();
        context.put("one", 1);
        context.put("two", 2);
        ExpressionParse expressionParse = new ExpressionParse();
        int result = expressionParse.parse(exp, context);
        System.out.println(result);
    }

}

/**
 * 环境类
 */
class Context {

    private final Map<String, Integer> map = new HashMap<>();

    public void put(String key, Integer value) {
        map.put(key, value);
    }

    public Integer get(String key) {
        Integer value = map.get(key);
        if (value == null) {
            throw new RuntimeException("参数不存在");
        }
        return value;
    }

}

/**
 * 抽象解释器
 */
abstract class Interpreter {

    public abstract int interpret(Context context);

}

/**
 * 非终结符表达式  +
 */
class AddInterpreter extends Interpreter {

    // 左终结符
    private Interpreter left;
    // 右终结符
    private Interpreter right;

    public AddInterpreter(Interpreter left, Interpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }
}

/**
 * 非终结符表达式  -
 */
class SubInterpreter extends Interpreter {

    // 左终结符
    private Interpreter left;
    // 右终结符
    private Interpreter right;

    public SubInterpreter(Interpreter left, Interpreter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }
}

/**
 * 终结符表达式
 */
class Variable extends Interpreter {

    private String key;

    public Variable(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Context context) {
        return context.get(key);
    }
}

/**
 * 语法解析器
 */
class ExpressionParse {

    public int parse(String expression, Context context) {
        List<String> exps = parseStr(expression);
        if (exps.size() != 3) {
            throw new RuntimeException("语法格式错误");
        }
        // 0-操作符 1/2为数值
        String[] exp = new String[3];
        for (String temp : exps) {
            if (isOperator(temp)) {
                exp[0] = temp;
            } else {
                if (exp[1] == null || exp[1].length() == 0) {
                    exp[1] = temp;
                } else {
                    exp[2] = temp;
                }
            }
        }

        return getExpression(exp[1], exp[2], exp[0]).interpret(context);
    }

    // 截取字符串
    private List<String> parseStr(String expression) {
        String[] exps = expression.trim().split(" ");
        return Arrays.asList(exps);
    }

    // 判断是否是非终结符
    private boolean isOperator(String symbol) {
        return "add".equals(symbol) || "sub".equals(symbol);
    }

    // 计算
    private Interpreter getExpression(String left, String right, String symbol) {
        if ("add".equals(symbol)) {
            return new AddInterpreter(new Variable(left), new Variable(right));
        } else if ("sub".equals(symbol)) {
            return new SubInterpreter(new Variable(left), new Variable(right));
        } else {
            throw new RuntimeException("操作符非法");
        }
    }

}
```

[design_pattern]:https://s2.ax1x.com/2020/02/15/1xjmCQ.md.png
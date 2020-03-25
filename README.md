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
组成（角色）|关系|作用  
:-|:-|:-  
抽象产品|具体产品的父类|描述产品的公共接口  
具体产品|抽象产品的子类，工厂创建的目标类|描述生产的具体产品  
工厂|被外界调用|根据传入不同的的参数，而创建不同的具体产品  
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
组成（角色）|关系|作用  
:-|:-|:-
抽象产品|具体产品的父类|描述具体产品的公共接口  
具体产品|抽象产品的子类，工厂类创建的目标|描述具体生产的产品  
抽象工厂|具体工厂的父类|描述具体工厂的公共接口  
具体工厂|抽象工厂的子类，被外界调用|描述具体工厂，实现工厂公共接口创建产品  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象产品家族|抽象产品的父类|描述抽象产品的公共接口  
抽象产品|具体产品的父类|描述具体产品的公共接口  
具体产品|抽象产品的子类，工厂类创建的目标类|描述生产的具体产品  
抽象工厂|具体工厂的父类|描述具体工厂的公共接口  
具体工厂|抽象工厂的子类，被外界调用|描述具体工厂，实现抽象工厂方法创建产品  
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
组成（角色）|关系|作用  
:-|:-|:-  
指挥者|指挥具体建造者，外部调用|调用适当的建造者来组件产品，类似于产品经理  
抽象建造者|所有具体建造者的父类|描述具体建造者的所有公共接口，类似于项目经理  
具体建造者|抽象建造者的子类，产品的具体实施着|实现抽象建造者的所有接口，类似于程序员  
产品|建造的对象|较为复杂的对象，类似于客户想要的产品  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象原型|具体原型的父类|声明克隆的方法  
具体原型|抽象原型的子类，外部调用|实现抽象原型中声明的克隆方法，返回克隆的对象  
客户类|让具体原型对象克隆自身而得到一个新的对象|克隆方法的调用者
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
组成（角色）|关系|作用  
:-|:-|:-  
适配者|适配器的父类|提供已有的功能接口
适配器|适配者的子类，同时也是目标抽象者的实现类|实现目标抽象者定义的接口  
目标抽象者|适配器的父类，外部访问|描述定义客户需要的接口  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象类|扩充抽象类的父类，拥有实现类接口的引用，外部调用|描述某一类型事物的公共方法    
扩充抽象类|抽象类的子类，外部调用|补充抽象类的额外公共方法  
实现类接口|具体实现类的父类|描述具体实现事物的公共方法  
具体实现类|实现类接口的子类|描述实现具体实现类事物的公公方法  
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
组成（角色）|描述  
:-|:-  
抽象构件角色|定义参加组合对象的共有方法和属性  
叶子构件|遍历的最小单位，下面再也没有分支  
树枝构件|结合树枝节点和子叶节点形成树形结构  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象角色|具体角色的父类|描述被装饰角色的公共属性方法  
具体角色|抽象角色的子类或实现类|实现所有被装饰角色的公共方法  
抽象装饰|具体装饰的父类|描述具体装饰的所有公共属性方法，包含抽象角色的饮用  
具体装饰|抽象装饰的子类，外部调用|实现所有抽象装饰的公共方法  
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
组成（角色）|作用
:-|:-  
外观角色|客户端调用他的方法，使得任务可以分发至子系统  
子系统角色|实际完成业务的系统，子系统可以被外观角色调用，也可以被客户端直接调用，不知道外观角色的存在  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象享元类|具体享元类的父类|描述具体享元类的公共方法属性  
具体享元类|抽象享元类的实现类|实现了已定的具体接口  
享元工厂类|外部调用|维护相同的享元对象，保证对象被共享  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象对象|真实对象和代理对象的父类|用来声明真实对象和代理对象的公共方法  
真实对象|抽象对象的子类|最终被执行方法的对象  
代理对象|抽象对象的子类，外界调用|包含一个真实对象的引用，将外界的操作传递至真实对象，同时添加自己独特的方法  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象访问者|具体访问者的父类|为每一个具体元素声明操作  
具体访问者|抽象访问者的子类|实现抽象访问者声明的操作  
抽象元素|具体元素的父类|定义描述元素的公共属性及方法，同时定义accept操作，以访问者为入参  
具体元素|抽象元素的子类|实现accept方法  
对象结构|外部访问，包含元素的对象集合|用于存放元素对象，且提供便利的内部元素方法  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象类|具体子类的父类|定义模板骨架，提取相同的方法  
具体子类|实现了抽象类|重写父类中定义的需要被重写的方法  
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
组成（角色）|关系|作用  
:-|:-|:-  
抽象策略类|具体策略类的父类|定义策略的公共属性  
具体策略类|抽象策略类的子类|继承抽象策略类，同时实现具体的策略内容  
环境类|包含一个抽象策略类的引用|连接上下文信息，给出策略结果  
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

[design_pattern]:https://s2.ax1x.com/2020/02/15/1xjmCQ.md.png
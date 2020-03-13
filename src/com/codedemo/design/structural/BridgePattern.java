/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

/**
 * @describe: 桥接模式
 * 数据多维度变化，有不同的组合，利用桥接模式，将维度抽象，在抽象层对其他维度数据进行连接
 * 例如：星巴克中包含中杯、大杯、超大杯三种杯型，有添加剂奶精、糖，添加剂和杯型可以组合出不同的搭配，此时利用桥接模式模式
 * @author: linjuanjuan
 * @date: 2020-02-16 15:29
 */
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
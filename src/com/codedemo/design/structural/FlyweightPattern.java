/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe: 享元模式 - 蝇量模式
 * 通过共享技术来有效的支持大量细粒度对象，最常见的就是String的定义，会放在常量池中
 * Flyweight 抽象享元类 定义共享对象具体业务的接口
 * ConcreteFlyweight 具体享元类 抽象享元类的实现，实现了已定的具体接口
 * UnsharedConcreteFlyweight 非共享具体享元类
 * FlyweightFactory 享元工厂类 创建具体的享元类，维护相同的享元对象，保证对象被共享
 * @author: linjuanjuan
 * @date: 2020-02-16 21:51
 */
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
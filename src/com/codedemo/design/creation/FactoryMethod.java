/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 工厂方法模式
 * 一个工厂创建一种产品，使用者不知道产品名称，仅知道工厂名字，不关心产品的生产过程
 * @author: linjuanjuan
 * @date: 2020-02-16 10:58
 */
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
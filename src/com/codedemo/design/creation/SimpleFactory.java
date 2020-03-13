/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 简单工厂模式
 * 通过抽象产品定义产品的所有属性
 * 一个工厂，可以创建所有产品，使用者仅需要知道产品的名字，不需要知道产品的具体生产过程
 * @author: linjuanjuan
 * @date: 2020-02-16 10:48
 */
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
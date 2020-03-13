/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 原型模式
 * 通过复制现有对象来创建新实例，常用于创建实例代价大的情况，利用object中的clone()方法进行复制
 * @author: linjuanjuan
 * @date: 2020-02-16 11:51
 */
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
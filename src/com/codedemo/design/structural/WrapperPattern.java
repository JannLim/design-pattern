/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

/**
 * @describe: 装饰模式
 * 在不改变愿有对象的基础上，给原有对象添加功能，是对继承关系的替代方案
 * Component 抽象角色「抽象类或接口」 抽象的构件对象，真实对象实现此相同的接口,不是必须的
 * ConcreteComponent 具体角色 即被装饰的对象可以有多个「因为被装饰的对象可以有多个」
 * Decorator 抽象装饰 持有一个抽象构件的引用，并且把客户端的请求转发给真实的对象，起到扩展真实对象的功能，不是必须的
 * ConcreteDecorator 具体的装饰 负责给构件对象扩展功能
 * 人穿衣服，穿不同的衣服取得不同的包装
 * @author: linjuanjuan
 * @date: 2020-02-16 18:49
 */
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




/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

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




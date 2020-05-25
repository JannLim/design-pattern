/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 单例模式 - 懒汉式
 * 私有构造函数，只有一个实例，全局提供一个访问点
 * 不默认初始化实例，什么时候用什么时候初始化，
 * 如果全局统一的访问点不加synchronized，线程不安全
 * jdk1.6以后对synchronized进行了优化，但是仍然存在一定的性能问题
 * @author: linjuanjuan
 * @date: 2020-02-16 10:38
 */
public class SingletonLazy {

    private static SingletonLazy instance;

    private SingletonLazy(){}

    // 这个地方加synchronized会导致当前类被整个锁定 static方法上加synchronized会锁定整个类
    public synchronized static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

}

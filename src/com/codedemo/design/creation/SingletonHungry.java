/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 单例模式 - 饿汉式
 * 私有构造函数，只有一个实例，全局提供一个访问点
 * 加载时就创建实例
 * @author: linjuanjuan
 * @date: 2020-02-16 10:43
 */
public class SingletonHungry {

    private static final SingletonHungry instance = new SingletonHungry();

    private SingletonHungry(){}

    public static SingletonHungry getInstance() {
        return instance;
    }

}

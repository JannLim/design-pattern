/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 单例模式 - 懒汉式
 * 私有构造函数，只有一个实例，全局提供一个访问点
 * 不默认初始化实例，什么时候用什么时候初始化
 * jvm会将代码转换为指令执行
 * 1.分配内存给对象
 * 2.初始化对象
 * 3.将初始化好的对象和内存地址建立连接，赋值
 * 4.用户初次访问
 * 以上步骤，2，3可能会进行指令重排序，用volatile解决
 * @author: linjuanjuan
 * @date: 2020-05-25 20:31
 */
public class SingletonLazyDoubleCheck {

    // 解决加锁后产生的指令重排序问题
    private volatile static SingletonLazyDoubleCheck instance;

    private SingletonLazyDoubleCheck(){}

    // 双重检查锁
    public static SingletonLazyDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletonLazyDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletonLazyDoubleCheck();
                }
            }
        }
        return instance;
    }

}

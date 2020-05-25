/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 线程单例
 * 使用ThreadLocal来实现多数据源动态切换
 * @author: linjuanjuan
 * @date: 2020-05-25 21:53
 */
public class SingletonThreadLocal {

    private SingletonThreadLocal () {}

    private static final ThreadLocal<SingletonThreadLocal> threadLocal =
            new ThreadLocal<SingletonThreadLocal>() {
                @Override
                protected SingletonThreadLocal initialValue() {
                    return new SingletonThreadLocal();
                }
            };

    public static SingletonThreadLocal getInstance() {
        return threadLocal.get();
    }

}

/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

import java.io.Serializable;

/**
 * @describe: 懒汉式单例
 * SingletonLazyHolder 里面的逻辑会等到外面的方法调用才执行
 * 巧妙的用了内部类的特性
 * JVM底层的实现逻辑，完美的避免了线程安全问题，也没有用到synchronized来降低性能
 * 这样的写法性能最优
 * @author: linjuanjuan
 * @date: 2020-05-25 20:47
 */
public class SingletonLazyInnerClass implements Serializable {

    // 尽管构造方法私有，但是可以利用反射机制进行创建对象
    private SingletonLazyInnerClass() {
        // 防止反射攻击
        if (SingletonLazyHolder.LAZY != null) {
            throw new RuntimeException("不允许构建实例");
        }
    }

    public static final SingletonLazyInnerClass getInstance() {
        return SingletonLazyHolder.LAZY;
    }

    private static class SingletonLazyHolder {
        private final static SingletonLazyInnerClass LAZY = new SingletonLazyInnerClass();
    }

    // 文件序列化与反序列化时会重新创建对象
    // 在jdk底层判断，如果readResolve方法存在，则利用该方法返回的对象，覆盖反序列化出来的对象
    // 对象还是创建了两次，只是在JVM层面，之前反序列化出来的对象，会被GC回收
    private Object readResolve() {
        return SingletonLazyHolder.LAZY;
    }

}

/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

/**
 * @describe: 对象适配者模式
 * 把适配类的api转换成目标类的api
 * 通过制定适配者来完成转换
 * @author: linjuanjuan
 * @date: 2020-02-16 14:51
 */
public class ObjectAdapterPattern {
    public static void main(String[] args) {
        ObjectTarget target = new ObjectAdapter(new ObjectAdaptee());
        target.request();
    }
}

/**
 * 目标类
 */
interface ObjectTarget {
    void request();
}

/**
 * 适配者类
 */
class ObjectAdaptee {
    public void adapteeRequest() {
        System.out.println("我是适配者");
    }
}

/**
 * 适配器
 */
class ObjectAdapter implements ObjectTarget {

    private ObjectAdaptee adaptee;

    public ObjectAdapter(ObjectAdaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.adapteeRequest();
    }
}
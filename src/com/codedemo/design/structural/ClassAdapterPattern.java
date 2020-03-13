/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

/**
 * @describe: 类适配器模式
 * 把适配类的api转换成目标类的api
 * 通过继承的方式继承适配类
 * @author: linjuanjuan
 * @date: 2020-02-16 14:44
 */
public class ClassAdapterPattern {
    public static void main(String[] args) {
        ClassTarget target = new ClassAdapter();
        target.request();
    }
}

/**
 * 目标类
 */
interface ClassTarget {
    void request();
}

/**
 * 适配者
 */
class ClassAdaptee {
    public void adapteeRequest(){
        System.out.println("我是适配者");
    }
}

/**
 * 适配器
 */
class ClassAdapter extends ClassAdaptee implements ClassTarget {
    @Override
    public void request() {
        super.adapteeRequest();
    }
}
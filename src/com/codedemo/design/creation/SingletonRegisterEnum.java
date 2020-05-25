/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 注册式单例 - 枚举 Effactive Java 推荐使用
 * 也是俄汉式的单例
 * 从JDK层面为枚举不被序列化和反射破坏保驾护航
 * @author: linjuanjuan
 * @date: 2020-05-25 21:23
 */
public enum SingletonRegisterEnum {

    INSTANCE;

    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static SingletonRegisterEnum getInstance() {
        return INSTANCE;
    }

}

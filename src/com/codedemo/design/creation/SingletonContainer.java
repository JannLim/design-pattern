/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe: 单例模式 - 容器式
 * 利用容器创建单例对象，类似于spring ioc
 * 对象方便管理，属于懒加载，但是会存在线程安全问题
 * @author: linjuanjuan
 * @date: 2020-05-25 21:38
 */
public class SingletonContainer {

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    private SingletonContainer () {}

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                try {
                    Object obj = Class.forName(className);
                    return obj;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                }
            } else {
                return ioc.get(className);
            }
        }
    }

}

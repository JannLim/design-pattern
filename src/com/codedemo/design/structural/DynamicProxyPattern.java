/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @describe: 动态代理模式
 * 为其他对象提供一个代理，以便控制这个对象的访问，使得用户不能真正访问目标对象
 * Subject 抽象对象 声明真实对象和代理对象的公共特点
 * RealSubject 真实对象 最终引用的对象
 * Proxy 代理对象 包含对真实对象的引用
 * 动态代理不继承或者抽象对象，利用jdk动态的在内存中创建对象，实现目标对象的代理功能，在编译时还没有实际的代理class类，
 * 在运行时动态生成，添加进jvm中的
 *
 * jdk采用的是读取接口信息，cglib是覆盖父类的方法，目的都是生成一个新的类，去实现增强代码逻辑的功能
 * jdk proxy对于用户而言，必须有一个接口实现，目标类相对来说复杂
 * cglib可以代理任意一个普通的类，没有要求
 * cglib 代理逻辑相对来说更加复杂，效率，调用效率更高，生成一个包含所有逻辑的FastClass，不需要反射调用
 * jdk proxy生成代理逻辑简单，执行效率较低，每次都需要反射调用
 *
 * cglib不能代理final的方法
 * @author: linjuanjuan
 * @date: 2020-02-16 22:47
 */
public class DynamicProxyPattern {
    public static void main(String[] args) {
        DynamicSubject subject = new DynamicRealSubject();
        DynamicSubject proxy = (DynamicSubject) new DynamicProxy(subject).getProxyInstance();
        proxy.buyMac();
    }
}

/**
 * 抽象对象
 * 必须实现接口，否则jdk无法进行动态代理
 */
interface DynamicSubject {
    void buyMac();
}

/**
 * 真实对象
 */
class DynamicRealSubject implements DynamicSubject {
    @Override
    public void buyMac() {
        System.out.println("买Mac");
    }
}

/**
 * 代理对象
 * 需要实现InvocationHandler
 */
class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // 返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理实例上处理方法调用并返回结果
        Object result = method.invoke(target, args);
        // 代理做的额外操作
        System.out.println("打包Mac，寄出");
        return result;
    }
}
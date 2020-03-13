/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 抽象工厂模式
 * 一个工厂可以创建多种产品，设立一个产品类库，不需要知道具体的产品是什么，使用者仅需要知道工厂，即可创建所有的产品
 * @author: linjuanjuan
 * @date: 2020-02-16 11:12
 */
public class AbstractFactory {
    public static void main(String[] args) {
        AbsFactory factoryA = new AbsFactoryA();
        factoryA.getContainer().create();
        factoryA.getModule().create();
        AbsFactory factoryB = new AbsFactoryB();
        factoryB.getContainer().create();
        factoryB.getModule().create();
    }
}

/**
 * 抽象产品家族
 */
abstract class ProductFamily {
    public abstract void create();
}

/**
 * 产品容器
 */
abstract class Container extends ProductFamily {
    @Override
    public abstract void create();
}

/**
 * 产品模具
 */
abstract class Module extends ProductFamily {
    @Override
    public abstract void create();
}

/**
 * 容器A
 */
class ContainerA extends Container {
    @Override
    public void create() {
        System.out.println("容器A创建");
    }
}

/**
 * 模具A
 */
class ModuleA extends Module {
    @Override
    public void create() {
        System.out.println("模具A创建");
    }
}

/**
 * 容器B
 */
class ContainerB extends Container {
    @Override
    public void create() {
        System.out.println("容器B创建");
    }
}

/**
 * 模具B
 */
class ModuleB extends Module {
    @Override
    public void create() {
        System.out.println("模具B创建");
    }
}

/**
 * 抽象工厂
 */
abstract class AbsFactory {
    public abstract Container getContainer();
    public abstract Module getModule();
}

/**
 * 工厂A
 */
class AbsFactoryA extends AbsFactory {
    @Override
    public Container getContainer() {
        return new ContainerA();
    }

    @Override
    public Module getModule() {
        return new ModuleA();
    }
}

/**
 * 工厂B
 */
class AbsFactoryB extends AbsFactory {
    @Override
    public Container getContainer() {
        return new ContainerB();
    }

    @Override
    public Module getModule() {
        return new ModuleB();
    }
}

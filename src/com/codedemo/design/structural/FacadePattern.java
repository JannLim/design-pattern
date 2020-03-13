/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

/**
 * @describe: 外观模式
 * 提供一个统一的接口，访问子系统的一群接口
 * Facade 外观角色 客户端调用他的方法
 * SubSystem 子系统角色 子系统可以被外观角色调用，也可以被客户端直接调用，不知道外观角色的存在
 * @author: linjuanjuan
 * @date: 2020-02-16 20:01
 */
public class FacadePattern {
    public static void main(String[] args) {
        // 程序员的一天，起床吃饭，写代码，晚上睡觉
        SubSystemA subSystemA = new SubSystemA();
        SubSystemB subSystemB = new SubSystemB();
        SubSystemC subSystemC = new SubSystemC();
        subSystemA.eating();
        subSystemB.coding();
        subSystemC.sleeping();

        System.out.println("===== 以下为外观模式 =====");
        Facade facade = new Facade(new SubSystemA(), new SubSystemB(), new SubSystemC());
        facade.programmersDay();
    }
}

/**
 * 子系统A
 */
class SubSystemA {
    public void eating() {
        System.out.println("吃饭");
    }
}

/**
 * 子系统B
 */
class SubSystemB {
    public void coding() {
        System.out.println("写代码");
    }
}

/**
 * 子系统C
 */
class SubSystemC {
    public void sleeping() {
        System.out.println("睡觉");
    }
}

/**
 * 外观角色 - 门面
 */
class Facade {
    private SubSystemA subSystemA;
    private SubSystemB subSystemB;
    private SubSystemC subSystemC;

    public Facade(SubSystemA subSystemA, SubSystemB subSystemB, SubSystemC subSystemC) {
        this.subSystemA = subSystemA;
        this.subSystemB = subSystemB;
        this.subSystemC = subSystemC;
    }

    public void programmersDay() {
        subSystemA.eating();
        subSystemB.coding();
        subSystemC.sleeping();
    }
}

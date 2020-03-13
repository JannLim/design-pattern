/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.creation;

/**
 * @describe: 建造者模式
 * 建造者知道产品建造的每一个步骤，指挥者去指挥建造者，根据指挥者的思想和设计的不同，使得建造步骤不同，产生的结果也就不同
 * idea中生成get、set方法中选择的Builder就是利用了建造者模式，可以代替多参数的构造器
 * mybatis逆向生成工具生成的Example，也是利用了建造者模式，选择的条件不同，顺序不同组合出的sql筛选条件也就不同
 * @author: linjuanjuan
 * @date: 2020-02-16 11:26
 */
public class BuilderPattern {
    public static void main(String[] args) {
        Builder builderA = new BuilderA();
        Director directorA = new Director(builderA);
        System.out.println(directorA.build());
        Builder builderB = new BuilderB();
        Director directorB = new Director(builderB);
        System.out.println(directorB.build());
    }
}

/**
 * 房子，产品的最终产物，房子需要安装门，窗户，地板
 */
class House {

    private String door;
    private String window;
    private String floor;

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "House{" +
                "door='" + door + '\'' +
                ", window='" + window + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}

/**
 * 抽象建造者，会装门，会装窗，会装地板
 */
interface Builder {
    void installDoor();
    void installWindow();
    void installFloor();

    House getHouse();
}

/**
 * 具体建造者A
 */
class BuilderA implements Builder {
    private House house = new House();

    @Override
    public void installDoor() {
        System.out.println("建造者A == > 安装门");
        house.setDoor("门");
    }

    @Override
    public void installWindow() {
        System.out.println("建造者A == > 安装窗");
        house.setWindow("窗");
    }

    @Override
    public void installFloor() {
        System.out.println("建造者A == > 安装地板");
        house.setFloor("地板");
    }

    @Override
    public House getHouse() {
        return house;
    }
}

/**
 * 具体建造者A
 */
class BuilderB implements Builder {
    private House house = new House();

    @Override
    public void installDoor() {
        System.out.println("建造者B == > 安装门");
        house.setDoor("门");
    }

    @Override
    public void installWindow() {
        System.out.println("建造者B == > 安装窗");
        house.setWindow("窗");
    }

    @Override
    public void installFloor() {
        System.out.println("建造者B == > 安装地板");
        house.setFloor("地板");
    }

    @Override
    public House getHouse() {
        return house;
    }
}

/**
 * 指挥者
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public House build() {
        builder.installDoor();
        builder.installFloor();
        builder.installWindow();
        return builder.getHouse();
    }
}
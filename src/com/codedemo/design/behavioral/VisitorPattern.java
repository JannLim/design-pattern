/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @describe: 访问者模式
 * 在不改变数据结构的情况下，增加一组作用于对象元素的新功能。
 * 在开发中，常常会对同一个对象产生不同的处理，如果我们都分别作处理，那么当该对象发生改变时，将会导致曾经的处理方式也需要跟着进行改变
 * 访问者模式对于解决这种问题提供了较好的方案，访问者模式就是为不同类型的元素提供多种访问操作方式，
 * 并且可以在不修改原有系统的情况下，增加新的访问方式
 * 访问者模式适用于数据结构较为稳定的，操作易于变化的
 * example：
 * 去医院开药时，医生会给出药品清单，里面包含各类药物，
 * 然后进行缴费，收银台人员会根据药品清单进行结算收款，
 * 缴费结束，需要到药房拿药，药房人员根据药品清单拿药，至此，整个流程结束。
 * 在以上描述中，药品作为具体元素，药品清单作为对象的结构，收银台人员与药房人员作为具体访问者
 * @author: linjuanjuan
 * @date: 2020-02-23 17:16
 */
public class VisitorPattern {

    public static void main(String[] args) {
        // 医生开药单
        Prescriptions prescriptions = new Prescriptions();
        prescriptions.add(new MedicineA("阿莫西林", BigDecimal.valueOf(15)));
        prescriptions.add(new MedicineB("999感冒灵",BigDecimal.valueOf(18)));

        // 付钱
        Cashier cashier = new Cashier("收银员1");
        prescriptions.accept(cashier);

        // 拿药
        Pharmacist pharmacist = new Pharmacist("药师1");
        prescriptions.accept(pharmacist);
    }

}

/**
 * 药品父类 - 抽象元素
 * 定义描述元素的公共属性及方法，同时定义accept操作，以访问者为入参
 */
abstract class Medicine {
    protected String name;
    protected BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract void accept(Visitor visitor);
}

/**
 * 药品 - 具体元素
 */
class MedicineA extends Medicine {

    public MedicineA(){}

    public MedicineA(String name, BigDecimal price) {
        super.name = name;
        super.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}

/**
 * 药品 - 具体元素
 */
class MedicineB extends Medicine {

    public MedicineB(){}

    public MedicineB(String name, BigDecimal price) {
        super.name = name;
        super.price = price;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}

/**
 * 抽象访问者
 * 为每一个具体元素声明操作，可以利用重载，也可以不用，具体的被调用在具体元素中，以后添加元素只需要修改访问者即可
 */
abstract class Visitor {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void visitor(MedicineA medicine);

    public abstract void visitor(MedicineB medicine);
}

/**
 * 收银员 - 具体访问者
 */
class Cashier extends Visitor {

    public Cashier(){}

    public Cashier(String name){
        super.name = name;
    }

    @Override
    public void visitor(MedicineA medicine) {
        System.out.println("收银员：" + name + " 给药品：" + medicine.getName() + " 收费：" + medicine.getPrice());
    }

    @Override
    public void visitor(MedicineB medicine) {
        System.out.println("收银员：" + name + " 给药品：" + medicine.getName() + " 收费：" + medicine.getPrice());
    }
}

/**
 * 药师 - 具体访问者
 */
class Pharmacist extends Visitor {

    public Pharmacist(){}

    public Pharmacist(String name){
        super.name = name;
    }

    @Override
    public void visitor(MedicineA medicine) {
        System.out.println("药师：" + name + " 取药：" + medicine.getName());
    }

    @Override
    public void visitor(MedicineB medicine) {
        System.out.println("药师：" + name + " 取药：" + medicine.getName());
    }
}

/**
 * 药单 - 对象结构
 */
class Prescriptions {
    private List<Medicine> medicines;

    public Prescriptions(){
        medicines = new ArrayList<>();
    }

    public void add(Medicine medicine) {
        medicines.add(medicine);
    }

    public void remove(Medicine medicine) {
        medicines.remove(medicine);
    }

    public void accept(Visitor visitor) {
        Iterator<Medicine> iterator = medicines.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(visitor);
        }
    }
}

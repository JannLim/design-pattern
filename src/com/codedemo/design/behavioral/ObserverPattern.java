/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 观察者模式
 * 观察者模式又被称为发布/订阅模式，属于行为型模式的一种
 * example:
 * 微信公众号，发布一篇文章，不同的微信用户订阅了该公众号，对该文章做出了不同的操作
 * 微信公众号作为目标，也就是被观察者，而用户作为了观察者，他们接收到被观察者发出的通知后，做出了操作
 * @author: linjuanjuan
 * @date: 2020-03-26 16:09
 */
public class ObserverPattern {

    public static void main(String[] args) {
        PublicAccount_A accountA = new PublicAccount_A();
        Customer customer = new Customer();
        Company company = new Company();
        accountA.subscribe(customer);
        accountA.subscribe(company);
        accountA.publish();
        System.out.println(" ============================= ");
        accountA.unsubscribe(customer);
        accountA.publish();
    }

}

/**
 * 微信公众号 - 抽象目标
 */
interface WechatPublicAccount {

    // 订阅
    void subscribe(WechatCustomer customer);

    // 取消订阅
    void unsubscribe(WechatCustomer customer);

    // 发布文章
    void publish();

}

/**
 * 微信用户 - 抽象观察者
 */
interface WechatCustomer {

    // 接收通知
    void handel();

}

/**
 * 微信公众号
 */
class PublicAccount_A implements WechatPublicAccount {

    private List<WechatCustomer> customers = new ArrayList<>();

    @Override
    public void subscribe(WechatCustomer customer) {
        customers.add(customer);
    }

    @Override
    public void unsubscribe(WechatCustomer customer) {
        customers.remove(customer);
    }

    @Override
    public void publish() {
        customers.forEach(temp -> temp.handel());
    }
}

/**
 * 客户 - 具体观察者
 */
class Customer implements WechatCustomer {

    @Override
    public void handel() {
        System.out.println("普通用户点击查看文章");
    }
}

/**
 * 企业客户
 */
class Company implements WechatCustomer {

    @Override
    public void handel() {
        System.out.println("企业用户点击查看文章");
    }
}

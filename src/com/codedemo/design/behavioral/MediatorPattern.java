/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

/**
 * @describe: 中介者模式
 * 用中介者封装对象的交互，中介者使得各对象不需要显示的交互，对象仅需要知道中介者即可，不需要知道其他同事
 * example:
 * 租房的经历想必大家都有过，中介是一个让人又爱又恨的角色，爱的是他手上的资源多，我们租房子不需要联系那么多的房东，只需要找一个中介，
 * 我们跟中介对接，他会帮我们按照我们的要求找到对应的房东。恨的是你需要给他中介费。
 * 在上述描述中，也就是一个很典型的中介者模式，如果没有中介，你租房子就只能依靠你强大的人际关系网络，但是中介的加入，让你只需要认识中介，
 * 就能租到满意的房子。
 * 其中：
 * 租客和房东，就属于同事
 * 中介就相当于是中介者
 * @author: linjuanjuan
 * @date: 2020-03-30 15:56
 */
public class MediatorPattern {

    public static void main(String[] args) {
        // 创建一个租客和房东都认识的中介
        MediatorStructure structure = new MediatorStructure();
        // 租客
        Tenant tenant = new Tenant("张三", structure);
        // 房东
        LandLord landLord = new LandLord("王武", structure);
        // 中介建立房东与租客的连接
        structure.setTenant(tenant);
        structure.setLandLord(landLord);

        // 租客询问房租
        tenant.contact("您好，请问您的四室一厅的房子一个月多少钱");
        // 房东回复
        landLord.contact("一个月6500元");
    }

}

/**
 * 抽象中介者
 */
abstract class Mediator {

    public abstract void connect(String message, Colleague colleague);

}

/**
 * 中介
 */
class MediatorStructure extends Mediator {

    private Tenant tenant;
    private LandLord landLord;

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public LandLord getLandLord() {
        return landLord;
    }

    public void setLandLord(LandLord landLord) {
        this.landLord = landLord;
    }

    @Override
    public void connect(String message, Colleague colleague) {
        // 租客发送信息，则房东可以得到信息，反之则反
        if (colleague instanceof Tenant) {
            System.out.println("租客" + colleague.getName() + "说：" + message);
            landLord.getMessage(message);
        } else {
            System.out.println("房东" + colleague.getName() + "说：" + message);
            tenant.getMessage(message);
        }
    }
}

/**
 * 抽象同事类
 */
abstract class Colleague {

    protected String name;
    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }
}

/**
 * 租客 - 具体同事
 */
class Tenant extends Colleague {

    public Tenant(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 联系
    public void contact(String message) {
        mediator.connect(message, this);
    }

    // 获取信息
    public void getMessage(String message) {
        System.out.println("租客" + name + "得到的信息：" + message);
    }
}

/**
 * 房东 - 具体同事
 */
class LandLord extends Colleague {

    public LandLord(String name, Mediator mediator) {
        super(name, mediator);
    }

    // 联系
    public void contact(String message) {
        mediator.connect(message, this);
    }

    // 获取信息
    public void getMessage(String message) {
        System.out.println("房东" + name + "得到的信息：" + message);
    }

}
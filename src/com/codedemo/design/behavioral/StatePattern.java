/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.math.BigDecimal;

/**
 * @describe: 状态模式
 * 允许对象在内部状态发生改变时改变他的行为，看起来好像修改了他的类
 * example:
 * 银行账户系统中，对于账号存在多种状态，不同的状态允许可不不同的操作。
 * 比如，账户状态分为：正常、冻结、注销，客户去银行存钱或者取钱，都需要先检查账户的状态，正常的账户才可以进行操作
 * 此时把账户状态抽象，其中正常、冻结、注销为具体状态，客户的操作即为环境
 * @author: linjuanjuan@hzlianyin.com
 * @date: 2020-03-26 10:44
 */
public class StatePattern {

    public static void main(String[] args) {
        // 正常客户
        CustomerOperate normal = new CustomerOperate(new NormalState());
        normal.saveMoney(BigDecimal.valueOf(100));
        normal.withdrawMoney(BigDecimal.valueOf(50));
        normal.withdrawMoney(BigDecimal.valueOf(100));
        System.out.println(" =========================== ");
        // 冻结客户
        CustomerOperate freeze = new CustomerOperate(new FreezeState());
        freeze.saveMoney(BigDecimal.valueOf(100));
        freeze.withdrawMoney(BigDecimal.valueOf(50));
        System.out.println(" =========================== ");
        // 注销客户
        CustomerOperate logout = new CustomerOperate(new LogoutState());
        logout.saveMoney(BigDecimal.valueOf(100));
        logout.withdrawMoney(BigDecimal.valueOf(50));
    }

}

/**
 * 账户状态 - 抽象状态
 */
interface AccountState {

    // 存钱
    void saveMoney(BigDecimal amt);

    // 取钱
    void withdrawMoney(BigDecimal amt);

}

/**
 * 正常状态 - 具体状态
 */
class NormalState implements AccountState {

    private BigDecimal balance;

    public NormalState() {
        balance = BigDecimal.ZERO;
    }

    @Override
    public void saveMoney(BigDecimal amt) {
        balance = amt.add(balance);
        System.out.println("账户状态正常，成功存入 == > " + amt);
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        if (balance.compareTo(amt) > 0) {
            System.out.println("账户状态正常，成功取出 == > " + amt);
        } else {
            System.out.println("账户状态正常，账户余额不足");
        }
    }
}

/**
 * 账户冻结 - 具体状态
 */
class FreezeState implements AccountState {

    @Override
    public void saveMoney(BigDecimal amt) {
        System.out.println("您的账户已被冻结");
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        System.out.println("您的账户已被冻结");
    }
}

/**
 * 注销状态 - 具体状态
 */
class LogoutState implements AccountState {

    @Override
    public void saveMoney(BigDecimal amt) {
        System.out.println("您的账户已被注销");
    }

    @Override
    public void withdrawMoney(BigDecimal amt) {
        System.out.println("您的账户已被注销");
    }
}

/**
 * 客户操作 - 环境类
 */
class CustomerOperate {

    private AccountState accountState;

    public CustomerOperate(AccountState accountState) {
        this.accountState = accountState;
    }

    // 存钱
    public void saveMoney(BigDecimal amt) {
        accountState.saveMoney(amt);
    }

    // 取钱
    public void withdrawMoney(BigDecimal amt) {
        accountState.withdrawMoney(amt);
    }

}
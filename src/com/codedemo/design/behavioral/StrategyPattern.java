/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.math.BigDecimal;

/**
 * @describe: 策略模式
 * 定义算法族，分别封装起来，让他们之间可以相互转换，算法的变化独立于使用算法的客户
 * example：
 * 商场针对不同的客户有不同的折扣比例，普通客户不享有折扣，vip客户享受9折，vvip客户享受85折，未来还可能有更高级的vip客户
 * 此时，针对于现有场景设计客户的价格计算采用策略模式
 * 客户类型 - 抽象策略类
 * 普通客户 / vip / vvip - 具体策略类
 * 结算 - 环境类
 * @author: linjuanjuan@hzlianyin.com
 * @date: 2020-03-25 16:38
 */
public class StrategyPattern {

    public static void main(String[] args) {
        BigDecimal amt = BigDecimal.valueOf(100);
        // 普通客户
        Settlement normal = new Settlement(new NormalCustomer());
        System.out.println("支付金额 == > " + normal.calPrice(amt));
        // vip
        Settlement vip = new Settlement(new VipCustomer());
        System.out.println("支付金额 == > " + vip.calPrice(amt));
        // vvip
        Settlement vvip = new Settlement(new VvipCustomer());
        System.out.println("支付金额 == > " + vvip.calPrice(amt));
    }

}

/**
 * 客户类型 - 抽象策略类
 */
interface CustomerType {

    // 计算最终价格
    BigDecimal calPrice(BigDecimal amt);

}

/**
 * 普通客户 - 具体策略类
 */
class NormalCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 普通客户");
        return amt;
    }
}

/**
 * vip - 具体策略类
 */
class VipCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 VIP");
        return amt.multiply(BigDecimal.valueOf(0.9));
    }
}

/**
 * vvip - 具体策略类
 */
class VvipCustomer implements CustomerType {

    @Override
    public BigDecimal calPrice(BigDecimal amt) {
        System.out.println("当前为 VVIP");
        return amt.multiply(BigDecimal.valueOf(0.85));
    }
}

/**
 * 结算 - 环境类
 */
class Settlement {

    private CustomerType customerType;

    public Settlement(CustomerType customerType) {
        this.customerType = customerType;
    }

    // 结算
    public BigDecimal calPrice(BigDecimal amt) {
        return customerType.calPrice(amt);
    }

}
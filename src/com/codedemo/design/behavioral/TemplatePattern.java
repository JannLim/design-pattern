/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

/**
 * @describe: 模板模式
 * 模板方法就是基于继承的代码复用技术，在模板模式中，我们可以将相同的方法放到父类中，不同的方法放到子类中
 * example:
 * 每个人都有一个厨师梦，炒菜其实拥有自己的固定套路：
 * 首先准备材料，然后给锅里倒油加热，加入材料进行翻炒，加入调味料，关火出锅
 * 不同的菜有不同的原材料和不同的调味料，这样，我们可以用模版模式定义炒菜的基本步骤，实现类去定义具体的原材料和调味料
 * @author: linjuanjuan
 * @date: 2020-03-25 09:39
 */
public class TemplatePattern {

    public static void main(String[] args) {
        // 清朝时蔬
        Vegetables vegetables = new Vegetables();
        vegetables.cook();
        System.out.println(" ======================== ");
        // 水煮肉片
        PoachedMeat poachedMeat = new PoachedMeat();
        poachedMeat.cook();
    }

}

/**
 * 抽象父类
 */
abstract class Cook {

    // 准备原材料
    protected abstract void prepareRawMaterials();

    // 倒油加热
    protected void addOil() {
        System.out.println("开火，倒油，加热");
    }

    // 加入材料进行翻炒
    protected void stirFry() {
        System.out.println("炒啊炒");
    }

    // 加入调料
    protected abstract void addCondiment();

    // 关火出锅
    protected void finish() {
        System.out.println("炒啊炒，装盘出锅");
    }

    // 炒菜
    public void cook() {
        prepareRawMaterials();
        addOil();
        stirFry();
        addCondiment();
        finish();
    }

}

/**
 * 清炒时蔬 - 具体子类
 */
class Vegetables extends Cook {

    @Override
    public void prepareRawMaterials() {
        System.out.println("准备青菜，洗净装盘");
    }

    @Override
    public void addCondiment() {
        System.out.println("顺序加入糖，盐，老抽，醋");
    }
}

/**
 * 水煮肉片 - 具体子类
 */
class PoachedMeat extends Cook {

    @Override
    public void prepareRawMaterials() {
        System.out.println("准备猪肉切片，准备青菜洗净，豆芽洗净");
    }

    @Override
    public void addCondiment() {
        System.out.println("加入火锅底料，花椒，糖，盐，老抽，醋");
    }
}
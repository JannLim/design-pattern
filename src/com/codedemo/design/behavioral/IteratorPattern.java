/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 迭代器模式
 * 提供一种方法顺序访问集合内的元素，不暴露它的内部结构
 * example:
 * 杭州市有两家网红餐饮店，一家早餐做得好，一家午餐受欢迎，两家店主商量了一下，准备合并两家店，那么这就涉及到需要合并两家店的菜单。
 * 可是早餐店的菜单使用ArrayList进行实现，早餐种类方便拓展，午餐店使用了数据进行实现，规定了午餐的种类。
 * 这个时候问题就来了，两家店都不愿意修改自己的内部结构向对方靠拢，但是，如果店铺合并，那么就需要合并菜单进行遍历。
 * 这时候，如果我们都需要知道聚合的内部结构，然后针对于内部结构进行遍历，那么如果在合并一家晚餐店，如果晚餐店采用的是另外的聚合方式来表示菜单
 * 那么问题就来了，这样的遍历不利于代码的维护。那么此时我们用迭代器模式，只需要两家店都维护出他们相应的迭代器，那么问题就迎刃而解，
 * 我们在遍历菜单的时候，就不再需要针对不同的聚合进行不同的遍历。
 * @author: linjuanjuan
 * @date: 2020-03-31 10:51
 */
public class IteratorPattern {

    public static void printMenu(MyIterator iterator) {
        if (iterator == null) {
            return;
        }
        while (iterator.hasNext()) {
            Food food = iterator.next();
            System.out.println("名称：" + food.getName() + "，价格：" + food.getPrice());
        }
    }

    public static void main(String[] args) {
        Breakfast breakfast = new Breakfast();
        printMenu(breakfast.iterator());
        Lunch lunch = new Lunch();
        printMenu(lunch.iterator());
        System.out.println(" =========== 添加早餐 =========== ");
        breakfast.add(new Food("豆浆", 3.0));
        printMenu(breakfast.iterator());
        printMenu(lunch.iterator());
    }

}

/**
 * 抽象迭代器，定义迭代器的公共方法
 */
interface MyIterator {

    // 是否有下一个
    boolean hasNext();
    // 获取下一个值
    Food next();

}

/**
 * 菜单 - 抽象聚合类
 */
abstract class Menu {

    // 创建迭代器
    public abstract MyIterator iterator();

}

/**
 * 食物
 */
class Food {
    private String name;
    private Double price;

    public Food(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * 早餐店
 */
class Breakfast extends Menu {

    private ArrayList<Food> foods = new ArrayList<>();

    public Breakfast() {
        foods.add(new Food("红薯粉", 5.0));
        foods.add(new Food("包子", 2.5));
    }

    void add(Food food) {
        foods.add(food);
    }

    void remove(Food food) {
        int k = -1;
        for (int i = 0; i < foods.size(); i++) {
            if (food.getName().equals(foods.get(i).getName()) && food.getPrice() == foods.get(i).getPrice()) {
                k = i;
                break;
            }
        }
        if (k == -1) {
            System.out.println("早餐不存在");
        } else {
            foods.remove(k);
        }
    }

    @Override
    public MyIterator iterator() {
        return new BreakfastIterator(foods);
    }
}

/**
 * 早餐店迭代器 - 具体迭代器
 */
class BreakfastIterator implements MyIterator {

    private List<Food> foods;
    private Integer i;

    public BreakfastIterator(List<Food> foods) {
        this.foods = foods;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        if (i < foods.size()) {
            return true;
        }
        return false;
    }

    @Override
    public Food next() {
        Food food = foods.get(i);
        i++;
        return food;
    }
}

/**
 * 午餐店
 */
class Lunch extends Menu {

    private final static Integer MAX = 6;
    private Food[] foods = new Food[MAX];

    public Lunch() {
        foods[0] = new Food("红烧肉", 8.0);
        foods[1] = new Food("鱼香肉丝", 10.0);
        foods[2] = new Food("水煮鱼", 15.0);
    }

    @Override
    public MyIterator iterator() {
        return new LunchIterator(foods);
    }
}

/**
 * 午餐迭代器 - 具体迭代器
 */
class LunchIterator implements MyIterator {

    private Food[] foods;
    private Integer i;

    public LunchIterator(Food[] foods) {
        this.foods = foods;
        i = 0;
    }

    @Override
    public boolean hasNext() {
        if (i >= foods.length) {
            return false;
        }
        Food food = foods[i];
        if (food == null) {
            return false;
        }
        return true;
    }

    @Override
    public Food next() {
        Food food = foods[i];
        i++;
        return food;
    }
}
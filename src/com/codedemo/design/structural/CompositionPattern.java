/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 组合模式
 * 也叫合成模式，用来描述部分与整体的关系，即将部分组装成整体，将对象组装成树形结构，使得用户对但个对象和组合对象的使用有一致性
 * Component 抽象构件角色 定义参加组合对象的共有方法和属性
 * Leaf 叶子构件 遍历的最小单位，下面再也没有分支
 * Composite 树枝构件 结合树枝节点和子叶节点形成树形结构
 * 集合中的addAll就是组合模式，Collection就是叶子构建
 * @author: linjuanjuan
 * @date: 2020-02-16 17:33
 */
public class CompositionPattern {

    public static void display(Composite root) {
        for (Component temp : root.getChildren()) {
            if (temp instanceof Leaf) {
                temp.getName();
            } else {
                temp.getName();
                display((Composite) temp);
            }
        }
    }

    public static void main(String[] args) {
        Composite root = new Composite();
        root.getName();
        Composite branch = new Composite();
        root.add(branch);
        Leaf leaf = new Leaf();
        branch.add(leaf);

        display(root);
    }
}

/**
 * 角色抽象
 */
abstract class Component {
    public abstract void getName();
}

/**
 * 叶子构建
 */
class Leaf extends Component {
    @Override
    public void getName() {
        System.out.println("我是叶子");
    }
}

/**
 * 树枝构建
 */
class Composite extends Component {
    private List<Component> components = new ArrayList<>();

    // 新增
    public void add(Component component) {
        components.add(component);
    }

    // 删除
    public void remove(Component component) {
        components.remove(component);
    }

    // 获得子类
    public List<Component> getChildren() {
        return components;
    }

    @Override
    public void getName() {
        System.out.println("我是树枝");
    }
}

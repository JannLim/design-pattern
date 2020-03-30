/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

/**
 * @describe: 备忘录模式 - 白箱模式
 * 备忘录角色对任何对象都提供一个接口，即宽接口。备忘录角色内部所存储的所有状态对所有对象公开，因此又叫做"白箱模式"。
 * @author: linjuanjuan
 * @date: 2020-03-27 11:42
 */
public class MementoWhitePattern {

    public static void main(String[] args) {
        // 初始化发起人对象
        WhiteOriginator old = new WhiteOriginator("初始化");
        System.out.println("初始化的对象 ==> " + old.getState());
        // 交给负责人存档
        WhiteCaretaker caretaker = new WhiteCaretaker();
        caretaker.saveMemento(old.saveMemento());
        old.setState("修改");
        System.out.println("修改后的对象状态 ==> " + old.getState());
        // 恢复存档
        old.restoreMemento(caretaker.getMemento());
        System.out.println("恢复存档的对象 ==> " + old.getState());
    }

}

/**
 * 备忘录 - 存储状态的对象，状态是公共方法，所以可以被任何对象所访问
 */
class WhiteMemento {
    private String state;

    public WhiteMemento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 发起人 - 需要被存储状态的对象
 */
class WhiteOriginator {
    private String state;

    public WhiteOriginator(String state) {
        this.state = state;
    }

    // 创建存档
    public WhiteMemento saveMemento() {
        return new WhiteMemento(state);
    }

    // 恢复存档
    public void restoreMemento(WhiteMemento memento) {
        this.state = memento.getState();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

/**
 * 负责人 - 仅仅负责传递备忘录，由于备忘录中存储的属性具有公共的get、set方法，所以可以被负责人所访问，访问不访问全靠开发人员自觉
 */
class WhiteCaretaker {

    private WhiteMemento memento;

    public void saveMemento(WhiteMemento memento) {
        this.memento = memento;
    }

    public WhiteMemento getMemento() {
        System.out.println("我是负责人，我知道备忘录中存储的信息 ==> " + memento.getState());
        return memento;
    }

}
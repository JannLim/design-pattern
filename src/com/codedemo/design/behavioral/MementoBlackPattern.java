/*
 * Copyright (c) 2020 LINJUANJUAN TECHNOLOGY Inc. All rights reserved.
 */
package com.codedemo.design.behavioral;

/**
 * @describe: 备忘录模式 - 黑箱模式
 * 备忘录为发起人提供宽接口，其他对象均提供窄接口。因此称之为"黑箱模式"
 * @author: linjuanjuan
 * @date: 2020-03-30 10:55
 */
public class MementoBlackPattern {

    public static void main(String[] args) {
        BlackOriginator originator = new BlackOriginator("初始化");
        System.out.println("发起人状态 ==> " + originator.getState());
        // 存档
        BlackCaretaker caretaker = new BlackCaretaker();
        caretaker.saveMemento(originator.saveMemento());
        originator.setState("修改");
        System.out.println("发起人状态 ==> " + originator.getState());
        // 恢复存档
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("发起人状态 ==> " + originator.getState());
    }

}

/**
 * 备忘录 - 对外开放的备忘录父类，不具备任何属性，保证外部对象访问时，只能拿到窄接口
 */
interface Memento {

}

/**
 * 发起人 - 拥有一个私有的备忘录类，继承了备忘录父类，这样就能保证备忘录仅为自己和发起人提供宽接口
 */
class BlackOriginator {

    private String state;

    public BlackOriginator(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // 存档
    public Memento saveMemento() {
        return new BlackMemento(state);
    }

    // 恢复存档
    public void restoreMemento(Memento memento) {
        this.state = ((BlackMemento) memento).getState();
    }

    // 提供窄接口的备忘录
    private class BlackMemento implements Memento {

        private String state;

        public BlackMemento(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }

}

/**
 * 负责人 - 拥有一个备忘录父类作为属性，但是由于父类中只提供了窄接口，所以什么都看不到
 */
class BlackCaretaker {
    private Memento memento;

    public Memento getMemento() {
        return this.memento;
    }

    public void saveMemento(Memento memento) {
        this.memento = memento;
    }
}

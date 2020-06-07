package com.codeman.concurrency.observer;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/26 20:44
 * @version: 1.0
 */
public abstract class Observer {
    private Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        subject.addObserver(this);
    }

    public abstract void update(String info);
}

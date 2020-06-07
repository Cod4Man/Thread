package com.codeman.concurrency.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/26 20:42
 * @version: 1.0
 */
public class Subject {
    private List<Observer> observerList = new ArrayList<>();

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
        noticeObserver();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 当信息发生改变时，通知观察者
     */
    private void noticeObserver() {
        observerList.stream()
                .forEach( observer -> observer.update(this.info));
    }
}

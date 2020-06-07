package com.codeman.concurrency.observer;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/26 20:49
 * @version: 1.0
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new ObserverImpl1(subject);
        new ObserverImpl2(subject);

        // 更新项目，观察者接到通知
        subject.setInfo("大降价大降价，快来抢购");
    }
}

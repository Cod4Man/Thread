package com.codeman.concurrency.countdown;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/6/4 22:19
 * @version: 1.0
 */
public class CustomerCountDown {
    private int count;
    private final int limitCount;

    public CustomerCountDown(int limitCount) {
        this.limitCount = limitCount;
    }

    public void count() {
        synchronized (this) {
            this.count++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this) {
            while (this.count < this.limitCount) {
                this.wait();
            }
        }
    }
}

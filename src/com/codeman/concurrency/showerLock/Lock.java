package com.codeman.concurrency.showerLock;

import java.util.Collection;
import java.util.concurrent.TimeoutException;

/**
 * @author: zhanghongjie
 * @description: 显式具备超时功能的外部锁
 * @date: 2020/5/17 15:49
 * @version: 1.0
 */
public interface Lock {

    void lock() throws InterruptedException, TimeoutException;

    void lock(long ms) throws InterruptedException, TimeoutException;

    void unlock() throws InterruptedException;

    Collection<Thread> getThreadQuene();

    int size();
}

package com.codeman.concurrency.showerLock;

import com.codeman.concurrency.utils.PrintUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.TimeoutException;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 15:55
 * @version: 1.0
 */
public class BooleanLock implements Lock {

    private Collection<Thread> threadQuene = new HashSet<>();
    private Thread currThread;
    private boolean lockFlag;

    @Override
    public void lock() throws InterruptedException, TimeoutException {
       lock(0L);
    }

    @Override
    public void lock(long ms) throws InterruptedException, TimeoutException {
        // synchronized(this) 相当于在方法上加上synchronized
        synchronized (this) {
            if (ms <= 0) {
                // 还没释放，则加入等待队列
                while (lockFlag) {
                    threadQuene.add(Thread.currentThread());
                    // 等待，释放锁
                    PrintUtils.printWithTimeAndThreadName("线程等待中...");
                    this.wait();
                }
            } else {
                // 还没释放，则加入等待队列
                long currTime = System.currentTimeMillis();
                while (lockFlag) {
                    if (System.currentTimeMillis() - currTime >= ms) {
                        throw new TimeoutException("时间超过");
                    }
                    threadQuene.add(Thread.currentThread());
                    // 等待，释放锁
                    PrintUtils.printWithTimeAndThreadName("线程等待中...");
                    this.wait(ms);
                }
            }
            // 有执行权, 从等待队列中删除
            currThread = Thread.currentThread();
            threadQuene.remove(currThread);
            // 标记置为true，这样其他线程就会被阻塞，当前线程去做业务处理，等手动调用unlock时候才放开
            lockFlag = true;
        }

    }

    @Override
    public void unlock() throws InterruptedException {
        synchronized (this) {
            if (currThread == Thread.currentThread()) {
                // 标记先与notify，因为唤醒后标记没有，那么会再次add()并且wait()
                PrintUtils.printWithTimeAndThreadName("线程释放");
                lockFlag = false;
                this.notifyAll();
            }
        }
    }

    @Override
    public Collection<Thread> getThreadQuene() {
        // 返回不可编辑的集合
        return Collections.unmodifiableCollection(threadQuene);
    }

    @Override
    public int size() {
        return threadQuene.size();
    }
}

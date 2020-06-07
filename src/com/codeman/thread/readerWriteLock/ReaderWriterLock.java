package com.codeman.thread.readerWriteLock;

public class ReaderWriterLock {
    // 正在读的个数
    private int readerReading = 0;
    // 等待读取的个数
    private int readerWaiting = 0;
    // 正在写的个数
    private int writerWriting = 0;
    // 等待读取的个数
    private int writerWaiting = 0;


    /**
     * 读锁
     * 有写操作时，先不读
     */
    public synchronized void readLock() {
        // 抢锁说明有线程想进行读操作
        this.readerWaiting++;
        // 有写操作时，挂起
        try {
            while (this.writerWriting > 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        } finally {
            this.readerWaiting--;
        }

        // 准备读操作
        this.readerReading++;
    }

    /**
     * 释放读锁
     */
    public synchronized void readUnlock() {
        // 正在读-1
        if (this.readerReading > 0)
            this.readerReading--;
        // 为什么要唤醒,释放读好像不用，释放写才要
        // 此处唤醒是为了给写锁通知，readerReading数量发生了改变，不满足while就可以继续了！！
        this.notifyAll();
    }


    /**
     * 写入锁
     * 什么情况写入需要锁：所有情况
     */
    public synchronized void writeLock() {
        this.writerWaiting++;
        try {
            while ((this.writerWriting > 0 || this.readerReading > 0) && (this.writerWaiting < this.readerWaiting)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        } finally {
            this.writerWaiting--;
        }
        this.writerWriting++;
    }

    /**
     * 释放写锁
     */
    public synchronized void writeUnLock() {
        if (this.writerWriting > 0)
            this.writerWriting--;
        // 唤醒
        this.notifyAll();
    }
}

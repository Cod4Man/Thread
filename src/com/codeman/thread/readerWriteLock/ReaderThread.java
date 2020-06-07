package com.codeman.thread.readerWriteLock;

public class ReaderThread extends Thread {

    private ReaderWriterLock lock;
    private DealData data;

    public ReaderThread(ReaderWriterLock lock, DealData data) {
        this.lock = lock;
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            lock.readLock();
            data.read();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.readUnlock();
        }
    }
}

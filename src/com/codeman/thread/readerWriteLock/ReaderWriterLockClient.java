package com.codeman.thread.readerWriteLock;

public class ReaderWriterLockClient {
    public static void main(String[] args) {
        final ReaderWriterLock LOCK = new ReaderWriterLock();
        DealData dealData = new DealData(15);
        new ReaderThread(LOCK, dealData).start();
        new ReaderThread(LOCK, dealData).start();
//        new ReaderThread(LOCK, dealData).start();
//        new ReaderThread(LOCK, dealData).start();
        new WriterThread(LOCK, dealData, "qwertyuiop").start();
        new WriterThread(LOCK, dealData, "QWERTYUIOP").start();
        new WriterThread(LOCK, dealData, "asdfghjkl").start();
    }
}

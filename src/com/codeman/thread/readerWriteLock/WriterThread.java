package com.codeman.thread.readerWriteLock;

public class WriterThread extends Thread {

    private ReaderWriterLock lock;
    private DealData data;
    private String dataStr;

    public WriterThread(ReaderWriterLock lock, DealData data, String dataStr) {
        this.lock = lock;
        this.data = data;
        this.dataStr = dataStr;
    }

    @Override
    public void run() {
        int index = 0;
        while (index < dataStr.length() - 1) {
            lock.writeLock();
            data.write(dataStr.charAt(index++));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.writeUnLock();

        }

    }
}

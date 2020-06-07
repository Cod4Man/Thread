package com.codeman.thread.worker;

/**
 * 工作线程
 */
public class WorkerThread extends Thread{

    private final Channel channel;

    public WorkerThread(final Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            channel.take();
            try {
                Thread.sleep(5_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

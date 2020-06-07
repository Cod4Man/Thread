package com.codeman.thread.worker;

public class TransThread extends Thread {
    private final Channel channel;
    private Request request;

    public TransThread(Channel channel, Request request) {
        this.channel = channel;
        this.request = request;
    }

    @Override
    public void run() {
        while (true) {
            channel.put(request);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

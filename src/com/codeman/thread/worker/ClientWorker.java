package com.codeman.thread.worker;

public class ClientWorker {
    public static void main(String[] args) {
        Channel channel = new Channel();
        channel.startWorker();
        new TransThread(channel, new Request("吃饭")).start();
        new TransThread(channel, new Request("睡觉")).start();
        new TransThread(channel, new Request("打豆豆")).start();


    }
}

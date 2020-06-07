package com.codeman.thread.runtime;

public class RuntimeTest {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("I am out.")));

        try {
            Thread.sleep(10_000);
            System.out.println("打断操作");
            throw new RuntimeException("Exception");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


package com.codeman.thread.threadException;

public class ThreadExceptionTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
//            Thread.sleep(3_000);
            int a = 1 /0;
        });
        t1.start();
        t1.setUncaughtExceptionHandler((t, e) -> {
//            System.out.println(t.getUncaughtExceptionHandler());
//            e.printStackTrace();
        });

    }
}

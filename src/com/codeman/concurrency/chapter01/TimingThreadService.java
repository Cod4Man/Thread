package com.codeman.concurrency.chapter01;

/**
 * @author: zhanghongjie
 * @description: 通过可以定时中断线程的工具
 * @date: 2020/5/7 21:41
 * @version: 1.0
 */
public class TimingThreadService {

    private Thread farThread;
    private boolean finished;

    public TimingThreadService(Runnable runner) {
        farThread = new Thread(() -> {
            Thread runnerr = new Thread(runner);
            runnerr.setDaemon(true);
            runnerr.start();
            try {
                runnerr.join();
                finished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        farThread.start();
    }

    /**
     * 超过时间还没结束则强制中断
     * @param limit 极限时间
     */
    public void stop(long limit) {
        long start = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - start >= limit) {
                // 超时，强制中断
                System.out.println("超时，强制中断！");
                farThread.interrupt();
                finished = true;
            }

            try {
                farThread.sleep(1); // 为何加了这个才会结束。因为farThread.interrupt()并不会真正中断，只是提醒他呗中断时要中断，比如sleep
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class TimingThreadServiceTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        TimingThreadService threadService = new TimingThreadService(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.stop(10_000);
        System.out.printf("花费时间为：%S", System.currentTimeMillis() - start);
    }
}

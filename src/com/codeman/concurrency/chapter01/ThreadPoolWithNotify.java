package com.codeman.concurrency.chapter01;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

import static com.codeman.concurrency.utils.PrintUtils.printWithTimeAndThreadName;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 14:00
 * @version: 1.0
 */
public class ThreadPoolWithNotify {

    private final static int MAX_THREAD_SIZE = 5;
    private final static Object LOCK = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        Stream<Thread> stream = Stream.of("t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9", "t10")
                .map(ThreadPoolWithNotify::createThreadWithName);
        stream.forEach(Thread::start);
        stream.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static Thread createThreadWithName(String name) {
        return new Thread(()->{
            // 控制最大运行线程数量
            // 同步是因为要控制的数量需要保证
           synchronized (LOCK) {
               count ++;
               // 线程数已满，不可再继续工作
               if (count >= MAX_THREAD_SIZE) {
                   try {
                       printWithTimeAndThreadName("等待中...");
                       LOCK.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }

           // 线程实际操作
            printWithTimeAndThreadName(",处理任务中。。。");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printWithTimeAndThreadName("处理任务结束！");

            // 释放锁
            synchronized (LOCK) {
                LOCK.notify();
                count--;
            }
            printWithTimeAndThreadName("释放锁");

        }, name);
    }
}

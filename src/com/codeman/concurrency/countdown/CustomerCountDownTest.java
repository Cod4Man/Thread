package com.codeman.concurrency.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/6/4 22:08
 * @version: 1.0
 */
public class CustomerCountDownTest {
    public static void main(String[] args) {
//        CountDownLatch countDownLatch = new CountDownLatch(5);
        CustomerCountDown countDown = new CustomerCountDown(5);
        System.out.println("============START===========");
        IntStream.rangeClosed(1, 5).forEach(i ->
                new Thread(() -> {
                    System.out.println("线程执行中....");
//                    countDownLatch.countDown();
                    countDown.count();
                }).start()

        );
        try {
//            countDownLatch.await();
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============END===========");
    }
}

package com.codeman.concurrency.pVolatile;

import com.codeman.concurrency.utils.PrintUtils;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/25 21:20
 * @version: 1.0
 */
public class VolatileTest {
    private static int maxValue = 10;
    private /*volatile*/ static int currValue = 0;

    public static void main(String[] args) {
        new Thread(()->{
            int localVlaue = currValue;
            while (localVlaue < maxValue) {
                if (localVlaue != currValue) {
                    PrintUtils.printWithTimeAndThreadName("currVlaue=" + currValue);
                    localVlaue = currValue;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Reader").start();


        new Thread(()->{
            int localVlaue = currValue;
            while (localVlaue < maxValue) {
                PrintUtils.printWithTimeAndThreadName("currVlaue=" + ++currValue);
                localVlaue = currValue;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}

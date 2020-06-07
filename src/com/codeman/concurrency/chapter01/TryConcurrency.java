package com.codeman.concurrency.chapter01;

import static sun.misc.Version.println;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/4/19 21:10
 * @version: 1.0
 */
public class TryConcurrency {

    public static void main(String[] args) {
        try {
            Thread.sleep(1000 * 100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

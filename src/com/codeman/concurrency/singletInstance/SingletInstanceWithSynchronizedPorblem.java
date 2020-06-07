package com.codeman.concurrency.singletInstance;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/24 17:15
 * @version: 1.0
 */
public class SingletInstanceWithSynchronizedPorblem {
    private static SingletInstanceWithSynchronizedPorblem instance;

    private SingletInstanceWithSynchronizedPorblem() {

    }

    public static SingletInstanceWithSynchronizedPorblem getInstance() {
        if (instance == null) {
            instance = new SingletInstanceWithSynchronizedPorblem();
        }

        return SingletInstanceWithSynchronizedPorblem.instance;
    }
}

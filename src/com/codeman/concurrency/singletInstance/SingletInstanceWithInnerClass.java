package com.codeman.concurrency.singletInstance;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/24 17:18
 * @version: 1.0
 */
public class SingletInstanceWithInnerClass {
    private static SingletInstanceWithInnerClass instance;

    private SingletInstanceWithInnerClass() {
        // empty
    }

    private static class SingletInstanceInnerClass {
        private final static SingletInstanceWithInnerClass INSTANCE = new SingletInstanceWithInnerClass();

    }

    public static SingletInstanceWithInnerClass getInstance() {
        return SingletInstanceInnerClass.INSTANCE;
    }
}

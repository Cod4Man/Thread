package com.codeman.concurrency.singletInstance;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/24 17:21
 * @version: 1.0
 */
public class SingletInstanceWithEnum {

    private SingletInstanceWithEnum() {
        // empty
    }

    private static enum InnerEnum {
        INSTANCE;
        private SingletInstanceWithEnum instance;
        InnerEnum() {
            instance = new SingletInstanceWithEnum();
        }

        public SingletInstanceWithEnum getInstance() {
            return instance;
        }
    }

    public static SingletInstanceWithEnum getInstance() {
        /*
        * 利用的是枚举的特性，只会实例化一次，并且线程安全
        * */
        return InnerEnum.INSTANCE.getInstance();
    }
}

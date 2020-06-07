package com.codeman.concurrency.singletInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/24 17:06
 * @version: 1.0
 */
public class TestSynchronized {
    public static void main(String[] args) {
        Map<SingletInstanceWithEnum, Integer> countMap = new HashMap<>();
        IntStream.rangeClosed(1, 1000)
                .forEach(i -> new Thread(() -> {
//                    SingletInstanceWithDoubleCheckNVolatile instance = SingletInstanceWithDoubleCheckNVolatile.getInstance();
//                    SingletInstanceWithSynchronizedPorblem instance = SingletInstanceWithSynchronizedPorblem.getInstance();
//                    SingletInstanceWithInnerClass instance = SingletInstanceWithInnerClass.getInstance();
                    SingletInstanceWithEnum instance = SingletInstanceWithEnum.getInstance();
                    countMap.put(instance,countMap.getOrDefault(instance, 1));
                }).start());
        System.out.println(countMap.keySet());
    }
}

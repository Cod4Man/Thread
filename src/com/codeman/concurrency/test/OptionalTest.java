package com.codeman.concurrency.test;

import java.util.Optional;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 14:57
 * @version: 1.0
 */
public class OptionalTest {
    public static void main(String[] args) {
//        System.out.println(getStr());
        Optional.ofNullable(getStr().toString()).ifPresent(System.out::println);
        System.out.println("that is ok!");
    }

    private static String getStr () {
        String str = null;
        return str;
    }
}

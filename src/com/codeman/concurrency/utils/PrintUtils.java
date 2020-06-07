package com.codeman.concurrency.utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 15:42
 * @version: 1.0
 */
public class PrintUtils {
    public static void printWithTime(String str) {
        System.out.printf("【%s】%s\n",
                LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
                str);
    }

    public static void printWithTimeAndThreadName(String str) {
        System.out.printf("【%s】线程【%s】%s\n",
                LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")),
                Thread.currentThread().getName(), str);
    }
}

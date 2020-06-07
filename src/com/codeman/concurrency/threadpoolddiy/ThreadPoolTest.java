package com.codeman.concurrency.threadpoolddiy;

import com.codeman.concurrency.utils.PrintUtils;

import java.util.stream.IntStream;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/21 23:12
 * @version: 1.0
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolDIY threadPoolDIY = new ThreadPoolDIY(20, ThreadPoolDIY.DEFALUT_DISCARD_POLICY);
        IntStream.range(1, 40)
                .forEach(i -> {
                    try {
                        threadPoolDIY.submit(() -> {
                            PrintUtils.printWithTimeAndThreadName("开始任务" + i);
                            try {
                                Thread.sleep(3_000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            PrintUtils.printWithTimeAndThreadName("任务结束" + i);
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        try {
            Thread.sleep(1_000);
//            threadPoolDIY.destory();
//            try {
//                threadPoolDIY.submit(()-> System.out.println("hello"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

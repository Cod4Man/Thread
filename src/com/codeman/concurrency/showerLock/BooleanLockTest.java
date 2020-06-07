package com.codeman.concurrency.showerLock;

import com.codeman.concurrency.utils.PrintUtils;

import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 16:20
 * @version: 1.0
 */
public class BooleanLockTest {
    public static void main(String[] args) {
        Lock lock = new BooleanLock();
        Stream.of("t1", "t2", "t3", "t4")
                .forEach(thread -> {
                  new Thread(()->{
                      try {
                          lock.lock(10_000);
                          work();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      } catch (TimeoutException e) {
                          PrintUtils.printWithTimeAndThreadName("超时");
                      } finally {
                          try {
                              lock.unlock();
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                      }
                  }, thread).start();
                });

        try {
            Thread.sleep(1_000);
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void work() {
        try {
            PrintUtils.printWithTimeAndThreadName("任务处理中....");
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

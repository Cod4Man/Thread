package com.codeman.concurrency.observer;

import com.codeman.concurrency.utils.PrintUtils;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/26 20:49
 * @version: 1.0
 */
public class ObserverImpl2 extends Observer {
    public ObserverImpl2(Subject subject) {
        super(subject);
    }

    @Override
    public void update(String info) {
        PrintUtils.printWithTime("ObserverImpl222.update info to :" + info);
    }
}

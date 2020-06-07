package com.codeman.concurrency.observer;

import com.codeman.concurrency.utils.PrintUtils;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/26 20:48
 * @version: 1.0
 */
public class ObserverImpl1 extends Observer {
    public ObserverImpl1(Subject subject) {
        super(subject);
    }

    @Override
    public void update(String info) {
        PrintUtils.printWithTime("ObserverImpl111.update info to :" + info);
    }
}

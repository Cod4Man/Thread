package com.codeman.concurrency.guardedSuspension;

import java.util.LinkedList;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/31 12:05
 * @version: 1.0
 */
public class ClientThread extends Thread {

    private RequestQueue requestQueue;

    public ClientThread(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {

    }
}

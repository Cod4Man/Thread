package com.codeman.concurrency.guardedSuspension;

import java.util.LinkedList;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/31 11:35
 * @version: 1.0
 */
public class RequestQueue {
    private LinkedList<Request> requests = new LinkedList<>();

    public void putRequest(Request request) {
        synchronized (requests) {
            requests.addLast(request);
            this.notifyAll();
        }
    }

    public Request getRequest() {
        synchronized (requests) {
            while (requests.size() <=0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
            return requests.removeFirst();
        }
    }
}

class Request {
    private String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

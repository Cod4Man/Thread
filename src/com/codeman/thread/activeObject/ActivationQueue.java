package com.codeman.thread.activeObject;

import java.util.LinkedList;

/**
 * 任务队列
 */
public class ActivationQueue {

    private final static int MAX_SIZE = 20;
    private final LinkedList<MethodRequest> activeObjectQueue;

    public ActivationQueue() {
        this.activeObjectQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest activeObject) {
        while (activeObjectQueue.size() >= MAX_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("中断异常" + e.getMessage());
                return;
            }
        }

        activeObjectQueue.addLast(activeObject);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        while (activeObjectQueue.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("中断异常" + e.getMessage());
                return null;
            }
        }

        this.notifyAll();
        return activeObjectQueue.removeFirst();
    }
}

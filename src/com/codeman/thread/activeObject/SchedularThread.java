package com.codeman.thread.activeObject;

/**
 * 定时任务：定时轮询，将ActiveObject任务封装成MethodRequest放到任务队列中
 */
public class SchedularThread extends Thread{

    private final ActivationQueue activationQueue;

    public SchedularThread(ActivationQueue methodRequests) {
        this.activationQueue = methodRequests;
    }

    public void invoke(MethodRequest request) {
        activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}

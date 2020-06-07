package com.codeman.thread.future;

public class FutureImpl<T> implements Future<T> {

    private T result;
    private volatile boolean isDone;

    @Override
    public T get() {
        synchronized (this) {
            while (!isDone) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
        return result;
    }

    public synchronized void done(T result) {
        this.isDone = true;
        this.notifyAll();
        this.result = result;
    }
}


package com.codeman.thread.activeObject;

public class FutureResult<T> implements Result<T> {

    private boolean hasResult;

    private Result<T> result;

    public synchronized void setResult(Result<T> result) {
        this.result = result;
        this.hasResult = true;
        this.notifyAll();
    }

    @Override
    public synchronized T getResultValue() {
        while (!hasResult) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                break;
            }
        }
        return result.getResultValue();
    }
}

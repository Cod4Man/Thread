package com.codeman.thread.activeObject;

public class RealResult<T> implements Result<T> {

    private T reslut;


    public void setReslut(T reslut) {
        this.reslut = reslut;
    }

    @Override
    public T getResultValue() {
        return reslut;
    }
}

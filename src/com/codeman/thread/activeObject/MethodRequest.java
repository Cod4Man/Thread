package com.codeman.thread.activeObject;

/**
 * 每个任务方法拆分成的独立的类
 * {@link ActiveObject}
 */
public interface MethodRequest<T> {

    void execute();
}

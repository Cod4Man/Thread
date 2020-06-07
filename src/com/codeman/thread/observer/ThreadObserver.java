package com.codeman.thread.observer;

/**
 * 线程状态观察者
 */
public interface ThreadObserver {
    void notice(SubjectThread.Message message);
}

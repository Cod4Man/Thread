package com.codeman.thread.observer;

public class ThreadObserverImpl implements ThreadObserver {
    @Override
    public void notice(SubjectThread.Message message) {
        System.out.println(message.getMessage());
    }
}

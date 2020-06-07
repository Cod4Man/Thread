package com.codeman.thread.observer;

public class ObserverTest {
    public static void main(String[] args) {
        ThreadObserver threadObserver = new ThreadObserverImpl();
        SubjectThread subjectThread = new SubjectThread(threadObserver);
        new Thread(subjectThread).start();
    }
}

package com.codeman.thread.activeObject;

public class MakeStringThread extends Thread {

    private char value;
    private ActiveObject activeObject;


    public MakeStringThread(char value, ActiveObject activeObject) {
        this.value = value;
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            String s = activeObject.makeString(++i, value);
            System.out.printf("【%s】 生产了： 【%s】\n", Thread.currentThread().getName(), s);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

package com.codeman.thread.activeObject;

public class DisplayStringThread extends Thread {

    private ActiveObject activeObject;

    private String text;

    public DisplayStringThread(ActiveObject activeObject, String text) {
        this.activeObject = activeObject;
        this.text = text;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            String s = activeObject.displayString(text);
            System.out.printf("【%s】 展示： 【%s】\n", Thread.currentThread().getName(), s);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

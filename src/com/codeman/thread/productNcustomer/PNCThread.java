package com.codeman.thread.productNcustomer;

public class PNCThread {

    private int number;
    private int remainder;
    private final Object LOCK = new Object();
    /**
     * 生产者
     */
    public synchronized void produce() {
        if (remainder < 10) {
//            LOCK.notifyAll();
            notifyAll();
            System.out.println("produce 【" + Thread.currentThread().getName() + "】made the " + (++number) + " noodle" + ", remainder=" + ++remainder);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            try {
//                LOCK.wait();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*if (remainder > 0) {
            LOCK.notify();
        }*/
    }

    /**
     * 消费者
     */
    public synchronized void customer() {
        if (remainder < 1) {
            try {
//                LOCK.wait();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
//            LOCK.notifyAll();
            notifyAll();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("customer【" + Thread.currentThread().getName() + "】 buy " + number-- + ", remainder=" + --remainder);
        }
    }
}

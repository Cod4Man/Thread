package com.codeman.concurrency.threadpoolddiy;

import com.codeman.concurrency.utils.PrintUtils;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: zhanghongjie
 * @description: 自定义简易线程池
 * @date: 2020/5/21 21:58
 * @version: 1.0
 */
public class ThreadPoolDIY extends Thread{

    private final static int DEFAULT_SIZE = 10;
    private final static int DEFALUT_QUEUE_SIZE = 200;
    private final static List<WorkTask> THREAD_QUEUE = new LinkedList<>();
    private final static LinkedList<Runnable> TASKS = new LinkedList<>();
    private final static ThreadGroup THREAD_GROUP = new ThreadGroup("TG");
    public static final DiscardPolicy DEFALUT_DISCARD_POLICY = () -> {
        throw new QueueSizeOutException("当前任务很多，请稍等再试");
    };
    private int size;
    private final static int ACTIVE_SIZE = 8;
    private final static int MIN_SIZE = 4;
    private final static int MAX_SIZE = 12;
    private int queueSize;
    private DiscardPolicy discardPolicy;
    private boolean destory;

    public ThreadPoolDIY() {
        this(DEFALUT_QUEUE_SIZE, DEFALUT_DISCARD_POLICY);
    }

    public ThreadPoolDIY(int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    public void submit(Runnable runnable) throws Exception{
        if (destory) {
            throw new RuntimeException("线程池已被销毁");
        }
        synchronized (TASKS) {
            if (TASKS.size() >= queueSize) {
                // 执行传入的策略，这就是策略模式码
                discardPolicy.discard();
            }
            TASKS.addLast(runnable);
            TASKS.notifyAll();
        }
    }

    /**
     * 中断线程池, 中断，就是让线程跳出run方法
     */
    public void destory() {
        System.out.println("destory0");
        // 等任务执行完成
        while (!TASKS.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int tempSize = size;
        while (tempSize > 0) {
            for (WorkTask workTask : THREAD_QUEUE) {
                // 该线程block状态，中断
                if (ThreadStatus.BLOCK == workTask.getStatus()) {
                    PrintUtils.printWithTimeAndThreadName(workTask.getName());
                    workTask.interrupt();
                    workTask.close();
                    tempSize --;
                } else {
                    // 非block状态，说明当前有任务再执行，直接将状态置为dead即可
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.destory = true;
    }

    private void init() {
        for (int i = 0; i < MIN_SIZE; i++) {
            createWorkTask(i);


            // 测试不加group
//            WorkTask workTask = new WorkTask();
//            workTask.start();
        }
        this.size = MIN_SIZE;
        this.start();
    }

    private void createWorkTask(int i) {
        WorkTask workTask = new WorkTask(THREAD_GROUP, "Thread-" + i);
        workTask.start();
        // 放在线程队列做管理
        THREAD_QUEUE.add(workTask);
    }

    @Override
    public void run() {
        while (!destory) {
            try {
                Thread.sleep(2000);
                System.out.println("当前任务数：" + TASKS.size());
                System.out.println("当前线程池大小：" + size);
                System.out.println("当前线程活跃数：" + THREAD_GROUP.activeCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (TASKS.size() > ACTIVE_SIZE && TASKS.size() < MAX_SIZE) {
                for (int i = size; i < ACTIVE_SIZE; i++) {
                    createWorkTask(i);
                }
                this.size = ACTIVE_SIZE;
            } else if (TASKS.size() > MAX_SIZE) {
                for (int i = size; i < MAX_SIZE; i++) {
                    createWorkTask(i);
                }
                this.size = MAX_SIZE;
            }

            if (TASKS.isEmpty()) {
                outter:while (size >ACTIVE_SIZE) {
                    // 逐个关闭线程
                    for (WorkTask workTask : THREAD_QUEUE) {
                        // 该线程block状态，中断
                        if (ThreadStatus.BLOCK == workTask.getStatus()) {
                            PrintUtils.printWithTimeAndThreadName(workTask.getName());
                            workTask.interrupt();
                            workTask.close();
                            size --;
                            if (size <= ACTIVE_SIZE) {
                                break outter;
                            }
                        } else {
                            // 非block状态，说明当前有任务再执行，直接将状态置为dead即可
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    private interface DiscardPolicy {
        void discard() throws QueueSizeOutException;
    }

    private static class QueueSizeOutException extends Exception {
        public QueueSizeOutException(String message) {
            super(message);
        }
    }

    private static enum ThreadStatus {
        FREE, RUNNING, DEAD, BLOCK
    }

    private static class WorkTask extends Thread {
        private volatile ThreadStatus status = ThreadStatus.FREE;

        public WorkTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public WorkTask() {

        }

        @Override
        public void run() {
            // 当前线程未消亡，则一直处理任务
            outter: while (this.status != ThreadStatus.DEAD) {
                Runnable currThread = null;
                synchronized (TASKS) {
                    // 如果线程队列中没有线程，则说明没有任务，wait住
                    while (TASKS.isEmpty()) {
                        try {
                            this.status = ThreadStatus.BLOCK;
                            TASKS.wait();
                        } catch (InterruptedException e) {
                            // 外界有中断操作，则断开，不再处理任务
                            break outter;
                        }
                    }
                    currThread = TASKS.removeFirst();
                }
                // TASKS，唤醒wait

                if (currThread != null) {
                    this.status = ThreadStatus.RUNNING;
                    currThread.run();
                    this.status = ThreadStatus.FREE;
                }
            }
        }

        public void close() {
            this.status = ThreadStatus.DEAD;
        }

        public ThreadStatus getStatus() {
            return status;
        }

    }

}

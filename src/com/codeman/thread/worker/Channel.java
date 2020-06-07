package com.codeman.thread.worker;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 传输带
 */
public class Channel {
    // 最大任务数
    private final int MAX_SIZE;
    // 流水线工人数量
    private final int MAX_WORKER;
    // 任务队列起点
    private int start;
    // 任务队列终点
    private int tail;
    // 当前产品个数
    private volatile int count;
    // 传输带上的产品
    private Request[] requests;
    // 工人任务线程
    private WorkerThread[] workerThreads;


    public Channel() {
        this(100, 20);
    }

    public Channel(int maxSize, int worker) {
        this.MAX_SIZE = maxSize;
        this.MAX_WORKER = worker;
        requests = new Request[MAX_SIZE];
        workerThreads = new WorkerThread[MAX_WORKER];
        this.start = 0;
        this.tail = 0;
        this.count = 0;
        // 一些参数初始化
        this.init();
    }

    private void init() {
//        IntStream.range(1, MAX_SIZE)
//                .forEach(i -> requests[i] = new Request());
        IntStream.range(0, MAX_WORKER)
                .forEach(i -> workerThreads[i] = new WorkerThread(this));
    }

    /**
     * 工人开始工作
     */
    public void startWorker() {
        Arrays.stream(workerThreads)
                .forEach(WorkerThread::start);
    }

    /**
     * 机器生产零件
     * @param request
     */
    public synchronized void put(Request request) {
        // 当前任务大于最大载荷，需要停一停
        while (count >= MAX_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                return;
            }
        }

        // 工作尚未饱和，添加任务
        requests[tail] = request;
        count++;
        tail = (tail + 1) % MAX_SIZE;
        // 通知工人干活
        this.notifyAll();
        System.out.printf("【%s】 添加了任务 ==》%s\t %s \n", Thread.currentThread().getName(), request.getValue(), count);
    }

    /**
     * 流水工人处理零件
     * @return
     */
    public synchronized Request take() {
        // 传输带上没有产品，工人可以休息一会
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }

        // 可以干活
        Request result = requests[start];
        start = (start + 1) % MAX_SIZE;
        count--;
        this.notifyAll();
        System.out.printf("【%s】 拿取了任务 ==》%s \t %s \n", Thread.currentThread().getName(), result.getValue(), count);
        return result;
    }
}

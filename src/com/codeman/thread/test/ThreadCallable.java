package com.codeman.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class ThreadCallable {

    private List<Student2> students = new ArrayList<>();

    public static void main(String[] args) {
        ThreadCallable threadCallable = new ThreadCallable();
        threadCallable.initData();
        try {
            threadCallable.printWithThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化数据
     */
    public void initData() {
        Student2 student = null;
        for (int i = 1; i < 500; i++) {
            student = new Student2("小" + i, i, i % 2 == 0? '女' : '男');
            students.add(student);
        }
    }

    public void printWithThread() throws InterruptedException {
        List<Student2> studentsTemp = students;
        long timeB = System.currentTimeMillis();
        class InnerThread implements Callable<List<Student2>> {
            List<Student2> results = new ArrayList<>();
            List<Student2> list = new ArrayList<>();

            public InnerThread(List<Student2> list) {
                this.list = list;
            }

            @Override
            public List<Student2> call() {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        Thread.sleep(10);
                        System.out.println(list.get(i));
                    }
                    return list;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5); // 创建5线程池
        List<Student2> results = new ArrayList<>();
//        InnerThread thread1 = new InnerThread(studentsTemp);
        InnerThread thread1 = new InnerThread(studentsTemp.subList(0,100));
        InnerThread thread2 = new InnerThread(studentsTemp.subList(100,200));
        InnerThread thread3 = new InnerThread(studentsTemp.subList(200,300));
        InnerThread thread4 = new InnerThread(studentsTemp.subList(300,400));
        InnerThread thread5 = new InnerThread(studentsTemp.subList(400,499));
        Future<List<Student2>> listFuture1 = executorService.submit(thread1);
        Future<List<Student2>> listFuture2 = executorService.submit(thread2);
        Future<List<Student2>> listFuture3 = executorService.submit(thread3);
        Future<List<Student2>> listFuture4 = executorService.submit(thread4);
        Future<List<Student2>> listFuture5 = executorService.submit(thread5);
        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            executorService.shutdownNow();
        }
        try {
            results.addAll(Optional.ofNullable(listFuture1).get().get());
            results.addAll(Optional.ofNullable(listFuture2).get().get());
            results.addAll(Optional.ofNullable(listFuture3).get().get());
            results.addAll(Optional.ofNullable(listFuture4).get().get());
            results.addAll(Optional.ofNullable(listFuture5).get().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(results.size());
        System.out.println("总共用时：" + (System.currentTimeMillis() - timeB)); // 150ms
    }

}

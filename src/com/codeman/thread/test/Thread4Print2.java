package com.codeman.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程遍历数据
 */
public class Thread4Print2 {

    private List<Student2> students = new ArrayList<>();
    public static void main(String[] args) {
        Thread4Print2 demo = new Thread4Print2();
        demo.initData();
//        try {
//            demo.printWithThread(); // 6063 601
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        demo.printM(); // 30460    2988
    }

    public void printWithThread() throws InterruptedException {
        List<Student2> studentsTemp = students;
        long timeB = System.currentTimeMillis();
        class InnerThread extends Thread {
            List<Student2> results = new ArrayList<>();
            List<Student2> list = new ArrayList<>();

            public InnerThread(List<Student2> list) {
                this.list = list;
            }

            @Override
            public void run() {
//                list.subList(start,end).forEach(System.out::println);
                try {
                    for (int i = 0; i < list.size(); i++) {
                        Thread.sleep(10);
                        System.out.println(list.get(i));
                    }
                    results.addAll(list);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        executorService.execute(thread1);
        executorService.execute(thread2);
        executorService.execute(thread3);
        executorService.execute(thread4);
        executorService.execute(thread5);
        executorService.shutdown();
        if (!executorService.awaitTermination(10, TimeUnit.MINUTES)) {
            executorService.shutdownNow();
        }
        /*InnerThread thread1 = new InnerThread(studentsTemp, 0, 20, results);
        InnerThread thread2 = new InnerThread(studentsTemp, 20, 40, results);
        InnerThread thread3 = new InnerThread(studentsTemp, 40, 60, results);
        InnerThread thread4 = new InnerThread(studentsTemp, 60, 80, results);
        InnerThread thread5 = new InnerThread(studentsTemp, 80, 99, results);
//        threads.add(thread1);
        thread1.start();
//        threads.add(thread2);
        thread2.start();
//        threads.add(thread3);
        thread3.start();
//        threads.add(thread4);
        thread4.start();
//        threads.add(thread5);
        thread5.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            List<Student2> resultss = thread1.results;
            System.out.println("resultss.size()=" + resultss.size());
            System.out.println("results.size()=" + results.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        System.out.println(thread1.results.size());
        System.out.println("总共用时：" + (System.currentTimeMillis() - timeB)); // 150ms
    }

    public void printM() {
        long timeB = System.currentTimeMillis();
//        this.students.forEach(System.out::println);
        try {
            for (int i = 0; i < this.students.size(); i++) {
                Thread.sleep(10);
                System.out.println(this.students.get(i));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("总共用时：" + (System.currentTimeMillis() - timeB)); // 150ms
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
}

class Student2 {
    private String name;
    private int age;
    private char gender;

    public Student2() {
    }

    public Student2(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}

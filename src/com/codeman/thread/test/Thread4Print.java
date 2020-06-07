package com.codeman.thread.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 多线程遍历数据
 */
public class Thread4Print {

    private List<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        Thread4Print demo = new Thread4Print();
        demo.initData();
        demo.printWithThread();
    }

    public void printWithThread() {
        List<Student> studentsTemp = students;
        long timeB = System.currentTimeMillis();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 400; i++) {
                    System.out.println(studentsTemp.get(i));
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 400; i < 800; i++) {
                System.out.println(this.students.get(i));
            }
        }).start();
        new Thread(()->{
            for (int i = 800; i < 1200; i++) {
                System.out.println(this.students.get(i));
            }
        }).start();
        new Thread(()->{
            for (int i = 1200; i < 1600; i++) {
                System.out.println(this.students.get(i));
            }
        }).start();
        new Thread(()->{
            for (int i = 1600; i < 2000; i++) {
                System.out.println(this.students.get(i));
            }
        }).start();
        System.out.println("总共用时：" + (System.currentTimeMillis() - timeB)); // 150ms
    }

    public void printM() {
        long timeB = System.currentTimeMillis();
        this.students.forEach(System.out::println);
        System.out.println("总共用时：" + (System.currentTimeMillis() - timeB)); // 150ms
    }

    /**
     * 初始化数据
     */
    public void initData() {
        Student student = null;
        for (int i = 1; i < 10000; i++) {
            student = new Student("小" + i, i, i % 2 == 0? '女' : '男');
            students.add(student);
        }
    }
}

class Student {
    private String name;
    private int age;
    private char gender;

    public Student() {
    }

    public Student(String name, int age, char gender) {
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

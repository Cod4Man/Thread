package com.codeman.concurrency.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author: zhanghongjie
 * @description:
 * @date: 2020/5/17 16:35
 * @version: 1.0
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(11, "zhangsan"));
        studentList.add(new Student(12, "lisi"));
        System.out.println(studentList);
        Collection<Student> students = Collections.unmodifiableCollection(studentList);
        students.stream().forEach(student -> student.setAge(15));
        System.out.println(studentList);
    }
}

class Student {
    private int age;
    private String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

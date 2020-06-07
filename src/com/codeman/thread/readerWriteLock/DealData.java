package com.codeman.thread.readerWriteLock;

/**
 * 处理数据
 */
public class DealData {
    private char[] dataBuff;

    public DealData() {
        this(10);
    }

    public DealData(int size) {
        dataBuff = new char[size];
        for (int i = 0; i < size; i++) {
            dataBuff[i] = '*';
        }
    }

    public char[] read() {
        char[] result = dataBuff.clone();
        System.out.println(result);
        return result;
    }

    public void write(char ch) {
        for (int i = 0; i < dataBuff.length; i++) {
            dataBuff[i] = ch;
        }
    }

    public int size() {
        return dataBuff.length;
    }
}

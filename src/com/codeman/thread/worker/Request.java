package com.codeman.thread.worker;

/**
 * 任务
 */
public class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Request{" +
                "value='" + value + '\'' +
                '}';
    }
}

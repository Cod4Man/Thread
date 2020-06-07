package com.codeman.concurrency.singletInstance;

/**
 * @author: zhanghongjie
 * @description: 单一模式：使用双重检查保证懒加载和性能，使用volatile保证构造实例化完全
 * @date: 2020/5/24 17:01
 * @version: 1.0
 */
public class SingletInstanceWithDoubleCheckNVolatile {
    private static volatile SingletInstanceWithDoubleCheckNVolatile instance;

    private Object object;

    private SingletInstanceWithDoubleCheckNVolatile() {
        // empty
        // 使用volatile保证构造实例化完全, 但是效率会有所降低。
        // volatile可保证该对象对所有的线程在内存中是可见的，但无法保证原子性
    }

    public static SingletInstanceWithDoubleCheckNVolatile getInstance() {
        if (instance == null) {
            synchronized (SingletInstanceWithDoubleCheckNVolatile.class) {
                if (instance == null) {
                    instance = new SingletInstanceWithDoubleCheckNVolatile();
                }
            }
        }
        return SingletInstanceWithDoubleCheckNVolatile.instance;
    }
}

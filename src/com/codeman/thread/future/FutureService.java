package com.codeman.thread.future;

public class FutureService {


    public <T> Future<T> submit(FutureTask<T> task) {
        FutureImpl<T> future = new FutureImpl<>();
        new Thread(() ->
            future.done(task.call())
        ).start();
        return future;
    }
}

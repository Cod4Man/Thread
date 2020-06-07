package com.codeman.thread.future;

public class FutureClient {
    public static void main(String[] args) {
        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            System.out.println("执行任务中...");
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finished";
        });
        System.out.println("123" + future.get());
        System.out.println("====================");
        System.out.println("do other things..");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("result=" + future.get());
    }
}

package com.codeman.thread.productNcustomer;

import java.util.stream.Stream;

public class PNCTest {
    public static void main(String[] args) {
        PNCThread pncThread = new PNCThread();
        Stream.of("p1", "p2", "p3", "p4").forEach(a ->
                new Thread(() -> {
                    while (true)
                        pncThread.produce();
                }, a).start()
        );
        Stream.of("c1", "c2", "c3", "c4").forEach(a ->
                new Thread(() -> {
                    while (true)
                        pncThread.customer();
                }, a).start()
        );
    }
}

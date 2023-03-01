package com.fizz.aqs;

import java.util.concurrent.locks.ReentrantLock;

public class AqsTest {

    public static void main(String[] args) throws Exception {
        ReentrantLock lock = new ReentrantLock(true);
        Thread t1 = new Thread(() -> {
            lock.lock();
            int a = 1;
            while (a == 1) {

            }
            lock.unlock();
        });
        t1.start();
        Thread.sleep(5000);

        Thread t2 = new Thread(() -> {
            lock.lock();
            int a = 1;
            while (a == 1) {

            }
            lock.unlock();
        });
        t2.start();
    }
}

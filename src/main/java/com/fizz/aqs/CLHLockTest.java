package com.fizz.aqs;

public class CLHLockTest {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        CLHLock clhLock = new CLHLock();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                clhLock.lock();
                for (int i = 0; i < 10; i++) {
                    count++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                clhLock.unLock();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        Thread t2 = new Thread(r);
        t2.start();
        Thread t3 = new Thread(r);
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(count);
    }
}

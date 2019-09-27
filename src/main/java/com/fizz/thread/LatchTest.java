package com.fizz.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author Fizz
 * @since 2019/9/24 13:38
 */
public class LatchTest {

    public static void main(String[] args) throws InterruptedException {
        //闭锁：CountDownLatch、FutureTask
        //1.计数器
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.countDown();
        countDownLatch.await();
        //2.信号量
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();//阻塞会导致jvm中断线程
        semaphore.acquireUninterruptibly();//阻塞不会导致jvm中断线程
        semaphore.release();
        //3.关卡

    }
}

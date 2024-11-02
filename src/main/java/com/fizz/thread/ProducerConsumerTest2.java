package com.fizz.thread;

import com.fizz.utils.ThreadUtils;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fizz
 * @since 2019/9/19 16:33
 */
public class ProducerConsumerTest2 {

    private static final BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

    private static final AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(), "消费者" + i).start();
            new Thread(new Producer(),"生产者" + i).start();
        }
    }

    public static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                ThreadUtils.sleepQuietly(new Random().nextInt(50));
                String data;
                try {
                    queue.put(data = "数据" + count.incrementAndGet());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "生产了一个: " + data);
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                ThreadUtils.sleepQuietly(new Random().nextInt(50));
                String data;
                try {
                    data = queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "消费了一个: " + data);
            }
        }
    }
}

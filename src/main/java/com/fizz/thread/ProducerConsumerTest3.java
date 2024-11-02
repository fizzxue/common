package com.fizz.thread;

import com.fizz.utils.ThreadUtils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Fizz
 * @since 2019/9/19 16:33
 */
public class ProducerConsumerTest3 {

    private static final Queue<String> queue = new LinkedList<>();

    private static int count = 0;

    private static final Semaphore producerSemaphore = new Semaphore(10);
    private static final Semaphore consumerSemaphore = new Semaphore(0);

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
                try {
                    producerSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "获取到一个许可，开始生产:" + queue.size());

                String data = "数据" + ++count;
                if (queue.offer(data)) {
                    System.out.println(Thread.currentThread().getName() + "生产了一个: " + data);
                    consumerSemaphore.release();
                } else {
                    System.out.println(Thread.currentThread().getName() + "生产失败");
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                ThreadUtils.sleepQuietly(new Random().nextInt(50));
                try {
                    consumerSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + "获取到一个许可，开始消费, 当前队列容量:" + queue.size());

                String data = queue.poll();

                if (data != null) {
                    System.out.println(Thread.currentThread().getName() + "消费了一个: " + data);
                    producerSemaphore.release();
                } else {
                    System.out.println(Thread.currentThread().getName() + "消费失败, 队列为空");
                }
            }
        }
    }
}

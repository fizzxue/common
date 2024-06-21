package com.fizz.thread;

import com.fizz.utils.NamedThreadFactory;
import com.fizz.utils.ThreadUtils;
import com.fizz.utils.WaitEnqueuePolicy;

import java.util.concurrent.*;

/**
 * 固定两个线程的线程池，每批只处理两个任务
 *
 * @author Fizz
 * @since 2024/6/22 00:48
 */
public class TwoFixedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService e = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new NamedThreadFactory("two-fixed-threadPool-thread"), new WaitEnqueuePolicy());

        while (true) {
            ThreadUtils.sleepQuietly(2000);
            CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
                ThreadUtils.sleepQuietly(2000);
                return "Task 1 completed!";
            }, e);

            CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
                ThreadUtils.sleepQuietly(1000);
                System.out.println(e);
                return "Task 2 completed!";
            }, e);

            try {
                System.out.println(future1.get());
                System.out.println(future2.get());
            } catch (InterruptedException | ExecutionException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

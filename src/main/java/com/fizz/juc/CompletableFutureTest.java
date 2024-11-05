package com.fizz.juc;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Fizz
 * @since 2024/11/5 22:29
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        Executor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    private int count = 0;
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "CompletableFutureTest-thread-" + ++count);
                    }
                });

        String input = "input";
        CompletableFuture<String> operate1Future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return operate1(input);
            }
        }, executor);

        CompletableFuture<Void> operate2Future = operate1Future.thenAcceptAsync(output -> operate2(output+ "Accept"), executor);

        CompletableFuture<Void> voidCompletableFuture = operate1Future.thenCompose(new Function<String, CompletionStage<Void>>() {
            @Override
            public CompletionStage<Void> apply(String s) {
//                System.out.println("thenCompose: " + System.currentTimeMillis() + ": " + Thread.currentThread().getName());
                return CompletableFuture.runAsync(() -> operate2(s + "Compose"), executor);
            }
        });

        operate2Future.join();
        voidCompletableFuture.join();
    }

    public static String operate1(String input) {
        System.out.println("operate1: " + System.currentTimeMillis() + ": " + Thread.currentThread().getName() + ": " + input);
        return input + ".output";
    }

    public static void operate2(String output) {
        System.out.println("operate2: " + System.currentTimeMillis() + ": " + Thread.currentThread().getName() + ": " + output);
    }
}

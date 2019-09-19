package com.fizz.common.cache.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Fizz
 * @since 2019/9/18 16:46
 */
public class Test {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    System.out.println("=======" + Thread.currentThread().getId());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 3;
            }
        });

        CompletableFuture<Integer> future = future1.thenApply(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer o) {
                System.out.println("//////" + Thread.currentThread().getId());
                return o * o;
            }
        }).whenComplete((integer, throwable) -> System.out.println(integer));
        System.out.println(future.get() + "\\\\\\\\\\");

    }
}

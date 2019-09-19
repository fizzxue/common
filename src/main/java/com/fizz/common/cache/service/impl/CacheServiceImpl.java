package com.fizz.common.cache.service.impl;

import com.fizz.common.cache.service.CacheService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 缓存服务类接口实现类
 *
 * @author Fizz
 * @since 2019/9/17 10:40
 */
@Service
public class CacheServiceImpl implements CacheService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*AsyncLoadingCache<String, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofSeconds(60))
                .maximumSize(3)
                .buildAsync(new AsyncCacheLoader<String, Integer>() {
                    @Override
                    public @NonNull CompletableFuture<Integer> asyncLoad(@NonNull String key, @NonNull Executor executor) {
                        System.out.println("enter");
                        try {
                            Thread.sleep(1000 * 5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("logout");
                        return executor.execute();
                    }
                });
        System.out.println(cache.get("1").get());
        System.out.println("this is async loading");*/
        long startTime = System.currentTimeMillis();
        Callable<Integer> calculateCallable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("进入call");
                Thread.sleep(2000);//模拟耗时时间
                int result = 1 + 2;
                return result;
            }
        };
        FutureTask<Integer> calculateFutureTask = new FutureTask<>(calculateCallable);
        Thread t1 = new Thread(calculateFutureTask);
        t1.start();
        //现在加入Thread运行的是一个模拟远程调用耗时的服务，并且依赖他的计算结果（比如网络计算器）
        try {
            //模拟耗时任务，主线程做自己的事情，体现多线程的优势
            System.out.println("主线程准备睡觉");
            Thread.sleep(3000);
            int a = 3 + 5;
            Integer result = calculateFutureTask.get();
            System.out.println(LocalDateTime.now());
            System.out.println("result = " + (a + result));//模拟主线程依赖子线程的运行结果
            System.out.println(LocalDateTime.now());
            long endTime = System.currentTimeMillis();
            System.out.println("time = " + (endTime - startTime) + "ms");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
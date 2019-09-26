package com.fizz.common.cache.service.impl;

import com.fizz.common.cache.service.CacheService;
import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * 缓存服务类接口实现类
 *
 * @author Fizz
 * @since 2019/9/17 10:40
 */
@Service
public class CacheServiceImpl implements CacheService {
    public static void main(String[] args) throws Exception {
        AsyncLoadingCache<Object, Object> cache = Caffeine.newBuilder().maximumSize(3)
                .expireAfterWrite(Duration.ofSeconds(0))
                /*.buildAsync(new CacheLoader<Object, Object>() {
                    @Nullable
                    @Override
                    public Object load(@NonNull Object key) throws Exception {
                        Thread.sleep(3000);
                        return key + "=====";
                    }
                });*/
                .buildAsync(new AsyncCacheLoader<Object, Object>() {
                    @Override
                    public @NonNull CompletableFuture<Object> asyncLoad(@NonNull Object key, @NonNull Executor executor) {
                        return CompletableFuture.supplyAsync(new Supplier<Object>() {
                            @Override
                            public Object get() {
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return key + "=====";
                            }
                        }, executor);
                    }
                });
        CompletableFuture<Object> future1 = cache.get("1");
        CompletableFuture<Object> future2 = cache.get("2");
        long start = System.currentTimeMillis();
        System.out.println("future1.get();" + future1.get());
        System.out.println("future2.get();" + future2.get());
        System.out.println(System.currentTimeMillis() - start);

    }
}
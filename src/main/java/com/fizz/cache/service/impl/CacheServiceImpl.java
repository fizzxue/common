package com.fizz.cache.service.impl;

import com.fizz.cache.service.CacheService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

/**
 * 缓存服务类接口实现类
 *
 * @author Fizz
 * @since 2019/9/17 10:40
 */
@Service
@Slf4j
public class CacheServiceImpl<K, V> implements CacheService<K, V> {

    private Cache<K, V> cache;

    public CacheServiceImpl() {
        cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterAccess(Duration.ofMinutes(5))
                .build();
    }

    @Override
    public V put(K key, V value) {
        cache.put(key, value);
        return value;
    }

    @Override
    public V get(@NonNull Object key) {
        return cache.getIfPresent(key);
    }

    @Override
    public Map<K, V> getAll(Iterable<? extends K> keys) {
        return cache.getAllPresent(keys);
    }

}

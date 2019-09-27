package com.fizz.common.cache.service;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

/**
 * 缓存服务类接口
 *
 * @author Fizz
 * @since 2019/9/17 10:39
 */
public interface CacheService<K, V> {

    V put(K key, V value);

    V get(@NonNull Object key);

    Map<K, V> getAll(Iterable<? extends K> keys);

}

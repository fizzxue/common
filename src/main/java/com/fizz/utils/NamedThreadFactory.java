package com.fizz.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Fizz
 * @since 2024/6/22 00:54
 */
public class NamedThreadFactory implements ThreadFactory {

    private final String threadNamePrefix;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public NamedThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = threadNamePrefix;
    }


    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format("%s-%d", threadNamePrefix, threadNumber.getAndIncrement()));
    }
}

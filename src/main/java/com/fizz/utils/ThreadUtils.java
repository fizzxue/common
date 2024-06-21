package com.fizz.utils;

/**
 * @author Fizz
 * @since 2024/6/22 01:13
 */
public class ThreadUtils {

    public static void sleepQuietly(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

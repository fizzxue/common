package com.fizz.ratelimit.example1;

public interface RateLimiter {

    boolean isOverLimit();

    long currentQPS();

    boolean visit();
}

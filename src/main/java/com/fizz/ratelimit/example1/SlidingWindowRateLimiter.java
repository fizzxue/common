package com.fizz.ratelimit.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * http://www.manongjc.com/detail/52-edhcklpwizeirjn.html
 */
public class SlidingWindowRateLimiter implements RateLimiter, Runnable{
    private static final int DEFAULT_BLOCK = 10;
    private static final int DEFAULT_ALLOWED_VISIT_PER_SECOND = 5;
    private final long maxVisitPerSecond;
    private final int block;
    private final AtomicLong[] countPerBlock;

    private AtomicLong count;
    private volatile int index;

    public SlidingWindowRateLimiter(int block, long maxVisitPerSecond) {
        this.block = block;
        this.maxVisitPerSecond = maxVisitPerSecond;
        countPerBlock = new AtomicLong[block];
        for (int i = 0 ; i< block ; i++) {
            countPerBlock[i] = new AtomicLong();
        }
        count = new AtomicLong(0);
    }

    public SlidingWindowRateLimiter() {
        this(DEFAULT_BLOCK, DEFAULT_ALLOWED_VISIT_PER_SECOND);
    }
    @Override
    public boolean isOverLimit() {
        return currentQPS() > maxVisitPerSecond;
    }

    @Override
    public long currentQPS() {
        return count.get();
    }

    @Override
    public boolean visit() {
        System.out.println("=======" + countPerBlock[index].incrementAndGet());
        System.out.println("=======" + count.incrementAndGet());
        return isOverLimit();
    }

    @Override
    public void run() {
//        if (isOverLimit()) {
//            System.out.println(1);
//        }
//        System.out.println(isOverLimit());
//        System.out.println(currentQPS());
//        System.out.println("index:" + index);
        index = (index + 1) % block;
        long val = countPerBlock[index].getAndSet(0);
        count.addAndGet(-val);
    }

    public static void main(String[] args) throws InterruptedException {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(10, 1);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(slidingWindowRateLimiter, 100, 100, TimeUnit.MILLISECONDS);

        Thread.sleep(900);
        System.out.println(slidingWindowRateLimiter.visit());
        Thread.sleep(100);
        System.out.println(slidingWindowRateLimiter.visit());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    slidingWindowRateLimiter.visit();
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    slidingWindowRateLimiter.visit();
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}

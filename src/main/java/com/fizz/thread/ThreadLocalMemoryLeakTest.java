package com.fizz.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 内存泄漏案例
 * https://code84.com/780705.html
 */
public class ThreadLocalMemoryLeakTest {
    public static class LocalVariable {
        //总共有5M
        private final byte[] local = new byte[1024 * 1024 * 5];

        // 开10个任务时，会被gc4次，因为后面4个任务调用set()会覆盖前4个任务的执行线程的entry里的value，value没引用就会gc
        @Override
        protected void finalize() throws Throwable {
            System.out.println(Thread.currentThread() + "===================finalize" + this);
        }
    }

    // (1)创建了一个核心线程数和最大线程数为 6 的线程池，这个保证了线程池里面随时都有 6 个线程在运行
    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(6, 6, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());
    // (2)创建了一个 ThreadLocal 的变量，泛型参数为 LocalVariable，LocalVariable 内部是一个 Long 数组
    static ThreadLocal<LocalVariable> localVariable = new ThreadLocal<LocalVariable>();

    public static void main(String[] args) throws InterruptedException {
        // (3)向线程池里面放入 10 个任务
        // 如果是10个任务会回收前4个线程的LocalVariable变量，因为后面4个任务调用set()会覆盖前4个任务的执行线程的entry里的value，value没引用就会gc
        for (int i = 0; i < 10; ++i) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    // (4) 往threadLocal变量设置值
                    LocalVariable localVariable = new LocalVariable();
                    // 会覆盖
                    ThreadLocalMemoryLeakTest.localVariable.set(localVariable);
                    localVariable = null;
                    // (5) 手动清理ThreadLocal
                    System.out.println("thread name end：" + Thread.currentThread().getName() + ", value:"+ ThreadLocalMemoryLeakTest.localVariable.get());
//                    ThreadLocalOutOfMemoryTest.localVariable.remove();
                }
            });
            Thread.sleep(1000);
        }

        // (6)是否让key失效，都不影响。只要持有的线程存在，都无法回收。thread-》threadlocalmap -》 threadlocal & localVariable
//        ThreadLocalOutOfMemoryTest.localVariable = null;
        System.out.println("pool execute over");
    }
}

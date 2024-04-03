package com.fizz;

/**
 * -Xms200m
 * -Xmx200m
 * -Xmn100m
 * -XX:SurvivorRatio=8
 * -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=C:\Users\fizz\Desktop\dump-idea.hprof
 * -XX:+PrintGCDetails
 * -XX:+PrintHeapAtGC gc前后打印堆分布
 * -Xloggc:C:\Users\fizz\Desktop\gc.log https://gceasy.ycrash.cn/gc-index.jsp
 */
public class GC {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        Byte[][] bb = new Byte[100][];
        for (int i = 0; i < 100; i++) {
            System.out.println("=====================  " + i);
            Byte[] b = new Byte[1024*1024*5];
            bb[i] = b;
            Thread.sleep(3000);
        }
    }
}

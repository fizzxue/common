package com.fizz.bf;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BloomFilterTest {

    public static void main(String[] args) {
//        testBloomFilterDefault();
        testBloomFilterWithFpp();
    }

    /**
     * 测试默认布隆过滤器失误率
     * size：原始数据量 10000
     * testSize：测试数据量 100000
     * fpp：失误率(失误数/测试数据量),未指定时，源码默认0.03(百分之3)
     * bit位数：36277*64=2321728bit约等于0.277M
     * hash函数数量：5
     */
    public static void testBloomFilterDefault() {
        // 原始数据量
        int size = 10000;

        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(
                        StandardCharsets.UTF_8), size);
        // 初始化数据
        for (int m = 0; m < size; m++) {
            bloomFilter.put("" + m);
        }
        List<Integer> list = new ArrayList<>();

        // 测试数据
        int testSize = 10000;
        // 以下全为新数据，若有判断存在的，将视为一次失误添加进list
        for (int n = size + testSize; n < size + 2 * testSize; n++) {
            if (bloomFilter.mightContain("" + n)) {
                list.add(n);
            }
        }
        double errorRate = Double.valueOf(list.size()) / testSize;
        System.out.println("误判率：" + errorRate);
        // 误判率：0.0312
    }

    /**
     * 测试指定失误率的布隆过滤器
     * size：原始数据量 10000
     * testSize：测试数据量 100000
     * fpp：失误率(失误数/测试数据量),这里指定为0.0001
     * bit位数：94669*64=6058816bit约等于0.722M
     * hash函数数量：13
     */
    public static void testBloomFilterWithFpp() {
        // 原始数据量
//        int size = 10000000;
        int size = 10;
        // 失误率
//        double fpp = 0.0001;
        double fpp = 0.00000001;

        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create(Funnels.stringFunnel(
                        StandardCharsets.UTF_8), size, fpp);
        // 初始化数据
        for (int m = 0; m < size; m++) {
            bloomFilter.put("" + m);
        }
        List<Integer> list = new ArrayList<>();

        // 测试数据
        int testSize = 10000000;
        // 以下全为新数据，若有判断存在的，将视为一次失误添加进list
        for (int n = size + testSize; n < size + 2 * testSize; n++) {
            if (bloomFilter.mightContain("" + n)) {
                list.add(n);
            }
        }
        double errorRate = Double.valueOf(list.size()) / testSize;
        System.out.println("list.size = " + list.size());
        System.out.println("误判率：" + new BigDecimal(errorRate + ""));
        // 误判率：1.0E-4，即0.0001
    }


}


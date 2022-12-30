package com.fizz.bf;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.Data;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class BFTest {
    public static void main(String[] args) throws Exception {
        StopWatch sw = new StopWatch();
        sw.start("bf put");
        BloomFilter<CharSequence> bf = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8),
                10000 * 3000, 0.01);
        Counter counter = new Counter();
        new Thread(() -> {
            long size = 0;
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("counter count: " + counter.getCount());
                if (size == counter.getCount()) {
                    break;
                }
                size = counter.getCount();
            }
        }).start();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\a_2000w.csv"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                bf.put(line.split(",")[0]);
                counter.incr();
            }
            System.out.println("approximateElementCount: " + bf.approximateElementCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }

        sw.stop();
//        System.out.println(sw.prettyPrint());
//        System.out.println(1/0);
        sw.start("去重");

        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\b_2000w.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Fizz\\Desktop\\c_2000w_bf.csv"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                // 肯定不重复，写新文件  多
                // 可能重复的db兜底   少
                if (!bf.mightContain(line.split(",")[0])) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    //todo db
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
            if (bw != null) {
                bw.flush();
                bw.close();
            }
        }
        sw.stop();
        System.out.println(sw.prettyPrint());
    }

    @Data
    static class Counter{
        private int count;

        public void incr(){
            count++;
        }
    }
}

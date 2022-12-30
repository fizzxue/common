package com.fizz.bf;

import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class InMemoryTest {
    public static void main(String[] args) throws Exception {
        StopWatch sw = new StopWatch();
        sw.start("set add");
        Set<String> set = new HashSet<>();
        new Thread(() -> {
            int size = 0;
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("set size: " + set.size());
                if (size == set.size()) {
                    break;
                }
                size = set.size();
            }
        }).start();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\a_3000w.csv"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                set.add(line.split(",")[0]);
            }
            System.out.println("set size: " + set.size());
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
        Thread.sleep(1000*1000);
        sw.start("去重");

        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\b_3000w.csv"));
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Fizz\\Desktop\\c_3000w_inmem.csv"));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (!set.contains(line.split(",")[0])) {
                    bw.write(line);
                    bw.newLine();
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
}

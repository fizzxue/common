package com.fizz.bf;

import com.fizz.common.constant.ArrayConstant;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GenData {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\Fizz\\Desktop\\b_3000w.csv"));
            int line = 10000 * 3000;
            for (int i = 0; i < line; i++) {
                bw.write(genDomain());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                bw.flush();
                bw.close();
            }
        }
    }

    public static String genDomain(){
        int domainLength = 5 + new Random().nextInt(15);
        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < domainLength; i++) {
//            sb.append(ArrayConstant.EN_NUM[new Random().nextInt(ArrayConstant.EN_NUM_LENGTH)]);
//        }
        sb.append(RandomStringUtils.randomAlphanumeric(domainLength));
        sb.append(".");
        sb.append(ArrayConstant.DOMAIN_SUFFIX[new Random().nextInt(ArrayConstant.DOMAIN_SUFFIX_LENGTH)]);
        sb.append(", ");
        sb.append(new Random().nextInt(100000));
        sb.append(", ");
        sb.append(RandomStringUtils.randomAlphanumeric(5 + new Random().nextInt(15)));
        return sb.toString();
    }

    public void test(){
        long start = System.currentTimeMillis();
        int size = 10000 * 5000;
        List<String> domainList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            domainList.add(genDomain());
        }
        System.out.println("time: " + (System.currentTimeMillis() - start));
        System.out.println("重复的个数为：" + (size - new HashSet<>(domainList).size()));
    }
}

package com.fizz.bf;

import java.io.*;

public class Compare {
    public static void main(String[] args) throws Exception {
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        try {
            boolean done = false;
            br1 = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\c_2000w_inmem.csv"));
            br2 = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\c_2000w_bf.csv"));
            int num = 0;
            while (true) {
                num++;
                String line1 = br1.readLine();
                String line2 = br2.readLine();
                if (line1 == null) {
                    break;
                }

                if (!line1.equals(line2) && !done) {
                    System.err.println("=============error==============");
                    done = true;
                }
            }
            System.out.println("=============success==============");
            System.out.println("=============num==============" + num);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br1 != null) {
                br1.close();
            }
            if (br2 != null) {
                br2.close();
            }
        }
    }
}

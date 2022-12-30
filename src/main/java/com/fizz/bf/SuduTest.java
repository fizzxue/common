package com.fizz.bf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SuduTest {

    public static void main(String[] args) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\Users\\Fizz\\Desktop\\size.txt"));
            int start = 0;
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                int now = Integer.valueOf(line);
                System.out.println(now - start);
                start = now;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }
}

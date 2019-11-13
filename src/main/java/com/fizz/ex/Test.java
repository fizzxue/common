package com.fizz.ex;

import java.util.ArrayList;

/**
 * @author Fizz
 * @since 2019/11/7 16:20
 */
public class Test {

    void t1(Object obj) {
        System.out.println("object");
    }

    void t1(String obj) {
        System.out.println("string");
    }

    public static void main(String[] args) {
        ArrayList<String>[] a = new ArrayList[2];
    }
}

package com.fizz.cl;

public class A {
    public void a() {
        System.out.println(B.class.getClassLoader());
        System.out.println(new B().getClass().getClassLoader());
        System.out.println("1111111111111111");
    }
}

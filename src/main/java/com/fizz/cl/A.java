package com.fizz.cl;

public class A {
    public void a() {
        System.out.println(App.class.getClassLoader());
        System.out.println(new App().getClass().getClassLoader());
        System.out.println("1111111111111111");
    }
}

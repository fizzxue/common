package com.fizz.common.thread;

/**
 * @author Fizz
 * @since 2019/9/19 16:33
 */
public class ThreadTest {
    class Foo {
        public Foo() {
            System.out.println("Foo super = " + super.toString());
            System.out.println("Foo this = " + this);
        }
    }

    class Sub extends Foo {
        public Sub() {
            System.out.println("Sub super = " + super.toString());
            System.out.println("Sub this = " + this);
        }
    }

    public static void main(String[] args) {
        new ThreadTest().new Foo();
        new ThreadTest().new Sub();
    }
}

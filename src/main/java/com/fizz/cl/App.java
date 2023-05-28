package com.fizz.cl;

public class App {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    MyClassLoader myClassLoader = new MyClassLoader();
                    Class<?> aClass = myClassLoader.loadClass("com.fizz.cl.A");
                    Object a = aClass.newInstance();
                    a.getClass().getDeclaredMethod("a").invoke(a);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).start();

    }
}

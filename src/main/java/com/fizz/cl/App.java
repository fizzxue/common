package com.fizz.cl;

public class App {

    public static void main(String[] args) throws Exception {
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000);
                    Class<?> aClass = new MyClassLoader().loadClass("");
                    Object a = aClass.newInstance();
                    a.getClass().getDeclaredMethod("a").invoke(a);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }).start();

    }
}

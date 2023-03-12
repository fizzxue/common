package com.fizz.cl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = new FileInputStream("/D:/IdeaProjects/common/target/classes/com/fizz/cl/A.class");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        byte[] bs;
        try {
            bs = new byte[resourceAsStream.available()];
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            resourceAsStream.read(bs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Class<?> aClass = defineClass("com.fizz.cl.A", bs, 0, bs.length);
        return aClass;
    }

}

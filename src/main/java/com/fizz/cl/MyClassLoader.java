package com.fizz.cl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MyClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (name.startsWith("com.fizz")) {
            return this.findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        Class<?> loadedClass = this.findLoadedClass(name);
//        if (loadedClass!=null) {
//            return loadedClass;
//        }
        InputStream resourceAsStream;
        try {
            resourceAsStream = new FileInputStream("D:/IdeaProjects/common/target/classes/com/fizz/cl/" +
                    name.substring(name.lastIndexOf(".") + 1) +".class");
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
        return defineClass(name, bs, 0, bs.length);
    }

}

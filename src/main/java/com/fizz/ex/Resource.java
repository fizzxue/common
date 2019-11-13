package com.fizz.ex;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Fizz
 * @since 2019/11/7 16:19
 */
public class Resource implements Closeable {

    @Override
    public void close() throws IOException {
        System.out.println("resource close");
        System.out.println(1 / 0);
    }
}

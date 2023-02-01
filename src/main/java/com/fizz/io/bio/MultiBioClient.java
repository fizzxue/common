package com.fizz.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MultiBioClient {

    public static void main(String[] args) throws Exception {
        a();
    }

    public static void a() {
        long start = System.currentTimeMillis();
        Runnable runnable = () -> {
            try {
                Socket socket = new Socket("localhost", 6380);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("我是客户端[" + socket.getLocalPort() + "]，我连接到服务器了").getBytes());
                outputStream.flush();
                InputStream inputStream = socket.getInputStream();
                int len = -1;
                byte[] bs = new byte[1024];
                if ((len = inputStream.read(bs)) != -1) {
                    System.out.println("收到服务端[" + socket.getPort() + "]消息：" + new String(bs, 0, len));
                }
            } catch (Exception e) {
//                System.out.println("==========================" + i);
                e.printStackTrace();
            }

        };
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
    }

    public static String b() {
        long start = System.currentTimeMillis();
        String str = "";
        try {
            Socket socket = new Socket("localhost", 6380);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("我是客户端[" + socket.getLocalPort() + "]，我连接到服务器了").getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            int len = -1;
            byte[] bs = new byte[1024];
            if ((len = inputStream.read(bs)) != -1) {
                str = "收到服务端[" + socket.getPort() + "]消息：" + new String(bs, 0, len);
                System.out.println(str);
            }
        } catch (Exception e) {
//                System.out.println("==========================" + i);
            e.printStackTrace();
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        return str;
    }
}

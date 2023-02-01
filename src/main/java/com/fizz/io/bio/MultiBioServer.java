package com.fizz.io.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiBioServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6380);
        while (true) {
            Socket accept = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    int port = accept.getPort();
                    InputStream inputStream = accept.getInputStream();
                    byte[] bs = new byte[1024];
                    int len = -1;
                    if ((len = inputStream.read(bs)) != -1) {
                        System.out.println("收到客户端[" + port + "]消息：" + new String(bs, 0, len));
                    }
                    OutputStream outputStream = accept.getOutputStream();
                    outputStream.write(("已收到客户端[" + port + "]消息").getBytes());
                    outputStream.flush();
                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}

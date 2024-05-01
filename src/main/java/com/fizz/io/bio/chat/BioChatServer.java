package com.fizz.io.bio.chat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioChatServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(6381);
        while (true) {
            Socket accept = serverSocket.accept();
            Thread t = new Thread(() -> {
                try {
                    int port = accept.getPort();
                    InputStream inputStream = accept.getInputStream();
                    OutputStream outputStream = accept.getOutputStream();
                    int len = -1;

                    while (true) {
                        byte[] bs = new byte[1024];
                        if ((len = inputStream.read(bs)) != -1) {
                            String s = new String(bs, 0, len);
                            if ("close".equals(s)) {
                                inputStream.close();
                                outputStream.close();
                                accept.close();
                            }

                            System.out.println("收到客户端[" + port + "]消息：" + s);
                            outputStream.write(("已收到客户端[" + port + "]消息: " + s).getBytes());
                            outputStream.flush();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
            t.start();
        }
    }
}

package com.fizz.io.bio.chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BioChatClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 6381);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        new Thread(() -> {
            try {
                while (true) {
                    String str = "";
                    int len = -1;
                    byte[] bs = new byte[1024];
                    if ((len = inputStream.read(bs)) != -1) {
                        str = "收到服务端[" + socket.getPort() + "]消息：" + new String(bs, 0, len);
                        System.out.println(str);
                    }
                }
            } catch (IOException e) {
                System.out.println("inputstream error");
//                throw new RuntimeException(e);
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        String prefix = "我是客户端[" + socket.getLocalPort() + "]，";
        while (scanner.hasNext()) {
//            long start = System.currentTimeMillis();
            String input = scanner.nextLine();
            if (input.contains("close")) {
                scanner.close();
                inputStream.close();
                outputStream.close();
                socket.close();
                System.out.println("close");
                break;
            }
            try {
                outputStream.write((prefix + input).getBytes());
                outputStream.flush();

            } catch (Exception e) {
                System.out.println("outputstream error");
                e.printStackTrace();
            }
//            System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        }
        scanner.close();
    }
}

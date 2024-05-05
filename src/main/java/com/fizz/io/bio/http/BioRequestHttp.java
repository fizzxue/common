package com.fizz.io.bio.http;

import cn.hutool.core.io.IoUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 使用bio发送http请求给tomcat，并读取响应
 */
public class BioRequestHttp {

    public static void main(String[] args) throws Exception {
        a();
    }

    public static String a() {
        long start = System.currentTimeMillis();
        String str = "";
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8081);
            System.out.println(socket.getLocalPort());
            OutputStream outputStream = socket.getOutputStream();
            String requestHeaderStr = "GET /examples/servlets/servlet/HelloWorldExample HTTP/1.1\r\n" +
                    "Host: localhost:8081\r\n" +
                    "\r\n";
            outputStream.write(requestHeaderStr.getBytes("utf-8"));
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            int len = -1;
            byte[] bs = new byte[1024];
            if ((len = inputStream.read(bs)) != -1) {
                System.out.println("收到服务端[" + socket.getPort() + "]消息：\r\n");
                System.out.println(new String(bs, 0, len));
            }

        } catch (Exception e) {
//                System.out.println("==========================" + i);
            e.printStackTrace();
        } finally {
            IoUtil.close(socket);
        }
        System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        return str;
    }


}

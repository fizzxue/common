package com.fizz.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9001);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel.connect(inetSocketAddress);

        while (!socketChannel.finishConnect()) {
            Thread.sleep(1000);
        }

        System.out.println("客户端[" + socketChannel.socket().getLocalPort()  +"]已连接");

        new Thread(() -> {
            try {
                Scanner scanner = new Scanner(System.in);
                while(scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    if ("close".equals(s)) {
                        socketChannel.close();
                        break;
                    }
                    socketChannel.write(ByteBuffer.wrap(s.getBytes()));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();

                 if (key.isReadable()) {
                    SocketChannel serverChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int n = serverChannel.read(buffer);
                    if (n == -1) {
                        serverChannel.close();
                    } else {
                        String message = new String(buffer.array(), 0, n).trim();
                        System.out.printf("服务端[%s]说: %s%n", serverChannel.socket().getPort(), message);
                    }
                }
            }
        }
    }

}

package com.fizz.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * https://blog.csdn.net/abc123lzf/article/details/80435511
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9001);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(inetSocketAddress);

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

                if(key.isConnectable()){
                    SocketChannel clientSocket = (SocketChannel)key.channel();
                    //这里需要检测是否完成连接
                    if(clientSocket.finishConnect()){
                        System.out.println("客户端[" + socketChannel.socket().getLocalPort()  +"]已连接");
                        //连接成功后需要将选择器设置为对该通道的读事件感兴趣（不然同样会无限循环）
                        key.interestOps(SelectionKey.OP_READ);
                    }else{
                        System.out.println("连接失败");
                        System.exit(1);
                    }
                } else if (key.isReadable()) {
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

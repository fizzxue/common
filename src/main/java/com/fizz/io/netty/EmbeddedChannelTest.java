package com.fizz.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * EmbeddedChannel模拟消息入站出站
 * LengthFieldBasedFrameDecoder处理粘包半包
 */
public class EmbeddedChannelTest {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 4),
                new LoggingHandler(LogLevel.DEBUG));

        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.buffer();
        writeBuf(byteBuf, "hello 123");
        writeBuf(byteBuf, "hello");
        writeBuf(byteBuf, "hello\n456\r\n789");
        channel.writeInbound(byteBuf);
    }

    private static void writeBuf(ByteBuf byteBuf, String content) {
        byte[] bytes = content.getBytes();
        byteBuf.writeInt(bytes.length);
        // 版本号
        byteBuf.writeByte(1);
        byteBuf.writeBytes(bytes);
    }
}

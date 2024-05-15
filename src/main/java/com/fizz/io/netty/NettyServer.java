package com.fizz.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast("handler1", new NettyServerHandler());
                            ch.pipeline().addLast("handler2", new NettyServerHandler2());
                        }
                    });

            ChannelFuture cf = bootstrap.bind(8080).sync();
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    static class NettyServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            String message = String.format("recv client[%s] data：%s", ctx.channel().remoteAddress(),
                    ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
            System.out.println(message);
            System.out.println("handler1 channelRead");
            ctx.fireChannelRead(msg);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ByteBuf buf = Unpooled.copiedBuffer("Hello, NettyClient!".getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(buf);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }

    static class NettyServerHandler2 extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            String message = String.format("recv client[%s] data：%s", ctx.channel().remoteAddress(),
                    ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
            System.out.println(message);
            System.out.println("handler2 channelRead");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {
            ByteBuf buf = Unpooled.copiedBuffer("Hello, NettyClient!".getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(buf);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}

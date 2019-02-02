/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.server;

import com.haobin.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.util.Date;

/**
 * @author HaoBin
 * @version $Id: ServerApp.java, v0.1 2019/1/22 14:39 HaoBin
 */
public class ServerApp {

    private static int PORT = 8000;

    public static void main(String[] args) {
        // 服务端启动引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // 接收连接线程组， 创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // 读取数据的线程组，读取数据以及业务逻辑处理
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                // 引导类配置 boss和worker
                .group(boss, worker)
                // 指定IO模型为NIO
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // ChannelInitializer是连接的读写处理链
                // NioSocketChannel是netty对NIO类型连接的抽象与上面的NioServerSocketChannel类似， 等同于 socketServer和socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ServerHandler());
                    }
                });
        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
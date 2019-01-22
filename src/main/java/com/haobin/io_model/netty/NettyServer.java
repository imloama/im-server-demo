/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.io_model.netty;

import com.haobin.io_model.netty.server_handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * netty的服务端
 *
 * @author HaoBin
 * @version $Id: NettyServer.java, v0.1 2019/1/15 17:06 HaoBin 
 */
public class NettyServer {

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
                // ChannelInitializer是连接的读写处理链
                // NioSocketChannel是netty对NIO类型连接的抽象与上面的NioServerSocketChannel类似， 等同于 socketServer和socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                // 绑定端口启动， 这是一个异步方法, 返回一个ChannelFuture
                .bind(8000)
                // 给ChannelFuture添加一个监听器，判断端口是否绑定成功
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("bind success");
                        } else {
                            System.out.println("bind failed");
                        }
                    }
                });
    }
}
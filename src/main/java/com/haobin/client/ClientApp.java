/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.client;

import com.haobin.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 客户端程序启动入口
 *
 * @author HaoBin
 * @version $Id: ClientApp.java, v0.1 2019/1/22 14:12 HaoBin
 */
public class ClientApp {


    public static int MAX_RETRY = 10;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);

    }

    /**
     * 客户端失败重连
     */
    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connected to server!");
            } else if (retry == 0) {
                System.out.println("retry user out");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 重连间隔设置为5S
                int delay = 5;
                System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
                // 定时任务重连， retry每次都减少一次
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay,
                        TimeUnit.SECONDS);
            }
        });
    }
}
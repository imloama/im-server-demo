/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.io_model.netty;

import com.haobin.io_model.netty.client_handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author HaoBin
 * @version $Id: NettyClient.java, v0.1 2019/1/15 17:16 HaoBin
 */
public class NettyClient {

    public static int MAX_RETRY = 5;

    public static void main(String[] args) throws InterruptedException{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 指定线程模型
        bootstrap.group(group)
                // IO模型为NIO
                .channel(NioSocketChannel.class)
                /// 处理逻辑
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        // ch.pipeline() 返回的是和这条连接相关的逻辑处理链，采用了责任链模式
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    /**
     * 失败重连的连接
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    public static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connnected!");
            } else if (retry == 0) {
                System.out.println("retry times used out");
            } else {
                // 第几次重连
                int order = (MAX_RETRY -retry) + 1;
                // 重连间隔设置为5S
                int delay = 5;
                System.out.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry-1), delay,
                        TimeUnit.SECONDS);
            }
        });
    }

}

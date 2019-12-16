/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.server;

import com.haobin.codec.PacketDecoder;
import com.haobin.codec.PacketEncoder;
import com.haobin.codec.Spliter;
import com.haobin.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author HaoBin
 * @version $Id: ServerApp.java, v0.1 2019/1/22 14:39 HaoBin
 */
public class Server {

    private Logger logger = LoggerFactory.getLogger(Server.class);


    public static void main(String[] args) {
        int serverPort = 8000;
        Server imServer = new Server(serverPort);
        imServer.start();
    }

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
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
                // 开启 tcp_nodelay(禁用 nagle)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // ChannelInitializer是连接的读写处理链
                // NioSocketChannel是netty对NIO类型连接的抽象与上面的NioServerSocketChannel类似， 等同于 socketServer和socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        // 登录请求
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        // 单聊信息处理
                        ch.pipeline().addLast(new MessageRequestHandler());
                        // 创建群聊
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        // 加入群聊
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        // 退群处理
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        // 成员列表处理
                        ch.pipeline().addLast(new ListGroupMemberRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap, port);
    }


    private void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                logger.info("{}   端口[{}]绑定成功", new Date(), port);
            } else {
                logger.info("端口[{}]绑定失败", port);
            }
        });
    }
}
/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.server;

import com.haobin.codec.PacketCodecHandler;
import com.haobin.codec.Spliter;
import com.haobin.server.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
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
                // 开启 tcp_nodelay (禁用 nagle)
                .childOption(ChannelOption.TCP_NODELAY, true)
                // ChannelInitializer是连接的读写处理链
                // NioSocketChannel是netty对NIO类型连接的抽象与上面的NioServerSocketChannel类似， 等同于 socketServer和socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 空闲检测需要在最前面，防止其他handler出错，导致链路传不到这里，形成误判
                        //ch.pipeline().addLast(new IdleStateHandler(6, 0, 0));
//                        ch.pipeline().addLast("http-codec", new HttpServerCodec()); // HTTP编码解码器
//                        ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536)); // 把HTTP头、HTTP体拼成完整的HTTP请求
//                        ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler()); // 分块，方便大文件传输，不过实质上都是短的文本数据
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(new IMIdleStateHandler());
//                        ch.pipeline().addLast(new IdleStateHandler(6, 0, 0));
                        //心跳请求
                        ch.pipeline().addLast(ServerHeartBeatHandler.INSTANCE);
                        // 登录请求
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        // 认证
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        // 单聊信息处理
                        ch.pipeline().addLast(MessageRequestHandler.INSTANCE);
                        // 创建群聊
                        ch.pipeline().addLast(CreateGroupRequestHandler.INSTANCE);
                        // 加入群聊
                        ch.pipeline().addLast(JoinGroupRequestHandler.INSTANCE);
                        // 退群处理
                        ch.pipeline().addLast(QuitGroupRequestHandler.INSTANCE);
                        // 成员列表处理
                        ch.pipeline().addLast(ListGroupMemberRequestHandler.INSTANCE);
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
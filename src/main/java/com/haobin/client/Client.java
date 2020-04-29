/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.client;

import com.haobin.client.console.ConsoleCommandManager;
import com.haobin.client.console.command.LoginConsoleCommand;
import com.haobin.client.handler.*;
import com.haobin.codec.PacketCodecHandler;
import com.haobin.codec.Spliter;
import com.haobin.protocol.request.HeartBeatRequestPacket;
import com.haobin.protocol.request.LoginRequestPacket;
import com.haobin.protocol.request.MessageRequestPacket;
import com.haobin.session.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 客户端程序启动入口
 *
 * @author HaoBin
 * @version $Id: ClientApp.java, v0.1 2019/1/22 14:12 HaoBin
 */
public class Client {

    private Logger logger = LoggerFactory.getLogger(Client.class);

    private int maxRetry;
    private String host;
    private int port;


    public static void main(String[] args) {
        Client nettyClient = new Client(5, "127.0.0.1", 8000);
        nettyClient.connectServer();
    }

    public Client(int maxRetry, String host, int port) {
        this.maxRetry = maxRetry;
        this.host = host;
        this.port = port;
    }

    public void connectServer() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                // 线程模型
                .group(workerGroup)
                // IO类型为NIO
                .channel(NioSocketChannel.class)
                // 设置选项
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                // IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(new IdleStateHandler(0, 5, 0));
                        //心跳请求
                        ch.pipeline().addLast(new ClientHeartBeatTimerHandler());
                        // 登录响应
//                        ch.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        // 消息接受响应
//                        ch.pipeline().addLast(MessageResponseHandler.INSTANCE);
                        // 创建群聊响应
//                        ch.pipeline().addLast(CreateGroupResponseHandler.INSTANCE);
                        // 加入群聊响应
//                        ch.pipeline().addLast(JoinGroupResponseHandler.INSTANCE);
                        // 退出群聊
//                        ch.pipeline().addLast(QuitGroupResponseHandler.INSTANCE);
                        // 成员列表响应
//                        ch.pipeline().addLast(ListGroupMemberResponseHandler.INSTANCE);
                    }
                });
        connect(bootstrap, host, port, maxRetry);
    }

    /**
     * 连接 server
     *
     * @param bootstrap 启动模型
     * @param host      目标主机
     * @param port      目标端口
     * @param retry     重试次数
     */
    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                logger.info("{} 连接成功", new Date());
                // 获取通道
                Channel channel = ((ChannelFuture) future).channel();
                // 读取控制台消息并发给服务端
//                startConsoleThread(channel);
//                LoginRequestPacket login = new LoginRequestPacket();
//                login.setUserName("admin");
//                login.setPassword("xxx");
//                login.setUserId("123");
//                channel.writeAndFlush(login);

                HeartBeatRequestPacket heart = new HeartBeatRequestPacket();
                channel.writeAndFlush(heart);
                System.out.println("================已经发送了心跳包---------");

//                MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
//                messageRequestPacket.setToUserId("123");
//                messageRequestPacket.setMessage("hello");
//                channel.writeAndFlush(messageRequestPacket);

            } else if (retry == 0) {
                logger.error("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (maxRetry - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                logger.error("{} 连接失败, 第 {} 次重连", new Date(), order);
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    /**
     * 接收控制台消息
     */
    private void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommandManager.exec(scanner, channel);
                }
            }
        }).start();
    }

}
package com.github.imloama.websocket;

import cn.hutool.core.util.RandomUtil;
import com.haobin.client.handler.ClientHeartBeatTimerHandler;
import com.haobin.codec.PacketCodecHandler;
import com.haobin.codec.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class SocketClient {

    public static void main(String[] args) throws InterruptedException {
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
//                        ch.pipeline().addLast(new Spliter());
//                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
//                        ch.pipeline().addLast(new IdleStateHandler(0, 5, 0));
                        //心跳请求
//                        ch.pipeline().addLast(new ClientHeartBeatTimerHandler());
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
        Channel channel = bootstrap.connect("localhost", 8000).sync().channel();
        channel.writeAndFlush("hello, socket!");

        Thread thread = new Thread(() -> {
            System.out.println("==============send data to server==============");
            channel.writeAndFlush("hello " + RandomUtil.randomInt(10,100));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();


    }

}

package com.haobin.client.handler;

import com.haobin.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

/**
 * @Author HaoBin
 * @Create 2019/12/18 20:54
 * @Description: 定时心跳
 **/
public class ClientHeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- Server is active ---");
        super.channelActive(ctx);
       // this.scheduleSendHeartBeat(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("--- Server is inactive ---");

        // 10s 之后尝试重新连接服务器
//        System.out.println("10s 之后尝试重新连接服务器...");
//        Thread.sleep(10 * 1000);
//        Client.doConnect();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("===============userEventTriggered=====" + evt.getClass().getName());
        if (evt instanceof IdleStateEvent) {
            // 不管是读事件空闲还是写事件空闲都向服务器发送心跳包
            ctx.writeAndFlush(new HeartBeatRequestPacket());
            System.out.println("channel is active = "+ctx.channel().isActive());
            // 提交心跳
            System.out.println("=================提交心跳请求================");
        }
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        /**
         * ctx.executor() 返回的是当前的 channel 绑定的 NIO 线程
         * NIO 线程有一个方法 schedule() 类似 jdk 的延时任务机制，可以隔一段时间之后执行一个任务
         */
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}

package com.haobin.client.handler;

import com.haobin.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author HaoBin
 * @Create 2019/12/18 20:54
 * @Description: 定时心跳
 **/
public class HearBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
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

package com.haobin.server.handler;

import com.haobin.protocol.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ServerHeartBeatHandler  extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {

    private static Logger logger = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

    public static final ServerHeartBeatHandler INSTANCE = new ServerHeartBeatHandler();

    int counter = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        //计数器?
        logger.debug("收到心跳请求===========" + channelHandlerContext.name());
        counter=0;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            logger.debug("服务器:IdleStateEvent, " + System.currentTimeMillis()/1000);
            // 空闲6s之后触发 (心跳包丢失)
            if (counter >= 3) {
                // 连续丢失3个心跳包 (断开连接)
                ctx.channel().close().sync();
                logger.debug("已与Client断开连接");
            } else {
                counter++;
                logger.debug("丢失了第 " + counter + " 个心跳包");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("连接出现异常");
        cause.printStackTrace();
        logger.error(cause.getMessage(), cause);

    }


}

package com.haobin.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author HaoBin
 * @Create 2019/12/18 20:35
 * @Description: 空闲检测
 *
 * 空闲检测，防止连接假死
 *
 * 可能连接假死的情况：
 * 1. 应用程序阻塞，无法读写
 * 2. 网络设备(如网卡)出现问题
 * 3. 网络抖动，丢包
 **/
public class IMIdleStateHandler extends IdleStateHandler {
    
    private Logger logger = LoggerFactory.getLogger(IMIdleStateHandler.class);

    public static final int READ_IDLE_TIME = 5;

    public IMIdleStateHandler() {
        super(READ_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        logger.info("空闲时间过长，断开连接");
        ctx.channel().close();
    }
}

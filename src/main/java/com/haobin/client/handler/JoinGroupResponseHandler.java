package com.haobin.client.handler;

import com.haobin.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author HaoBin
 * @Create 2019/12/16 9:57
 * @Description: 加入群聊响应处理
 **/
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    private Logger logger = LoggerFactory.getLogger(JoinGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket response) throws Exception {
        if (response.isSuccess()) {
            logger.info("加入群[{}]成功!", response.getGroupId());
        } else {
            logger.info("加入群[{}]失败!", response.getGroupId());
        }
    }
}

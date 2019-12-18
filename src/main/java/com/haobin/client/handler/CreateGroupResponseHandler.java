package com.haobin.client.handler;

import com.haobin.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author HaoBin
 * @Create 2019/12/13 17:05
 * @Description: 创建群聊响应
 **/
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    
    private Logger logger = LoggerFactory.getLogger(CreateGroupResponseHandler.class);

    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    private CreateGroupResponseHandler() {}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        logger.info("群创建成功，id 为[{}]", createGroupResponsePacket.getGroupId());
        logger.info("群里面有：{}", createGroupResponsePacket.getUserNameList());
    }
}

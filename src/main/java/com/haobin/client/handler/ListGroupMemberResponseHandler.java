package com.haobin.client.handler;

import com.haobin.protocol.response.ListGroupMemberResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:50
 * @Description:
 **/
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {

    private Logger logger = LoggerFactory.getLogger(ListGroupMemberResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket responsePacket) throws Exception {
        logger.info("群[{}]中的人包括：[{}]", responsePacket.getGroupId(), responsePacket.getSessionList());
    }
}

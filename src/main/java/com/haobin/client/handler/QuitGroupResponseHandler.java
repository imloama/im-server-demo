package com.haobin.client.handler;

import com.haobin.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:09
 * @Description: 退出群聊客户端处理
 **/
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    private Logger logger = LoggerFactory.getLogger(QuitGroupResponseHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) throws Exception {
        if (responsePacket.isSuccess()) {
            logger.info("退出群聊[{}]成功！", responsePacket.getGroupId());
        } else {
            logger.info("退出群聊[{}}]失败！", responsePacket.getGroupId());
        }
    }
}

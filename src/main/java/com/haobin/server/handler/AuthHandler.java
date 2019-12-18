/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.server.handler;

import com.haobin.session.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 身份校验
 * @author HaoBin
 * @version $Id: AuthHandler.java, v0.1 2019/2/19 11:25 HaoBin
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    private AuthHandler() {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 登录之后若没有session标志位则关闭channel
        if (!SessionUtil.hasLogin(ctx.channel())) {
            ctx.channel().close();
        } else {
            // 通过校验之后将 authHandler 从逻辑 pipeline 中删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }
}
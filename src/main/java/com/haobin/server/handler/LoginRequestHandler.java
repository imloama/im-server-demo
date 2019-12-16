/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.server.handler;

import com.haobin.protocol.request.LoginRequestPacket;
import com.haobin.protocol.response.LoginResponsePacket;
import com.haobin.session.Session;
import com.haobin.session.SessionUtil;
import com.haobin.utils.IDUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import java.util.UUID;

/**
 * @author HaoBin
 * @version $Id: LoginRequestHandler.java, v0.1 2019/2/18 21:34 HaoBin
 * 注：
 * @ChannelHandler.Sharable 显示地告诉 Netty，这个 handler 是支持多个 channel 共享的
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {}

    /**
     * 继承于SimpleChannelInboundHandler， 此类带泛型， 需要重写channelRead0
     * 这个类可以自动处理该 handler 可以处理的对象(泛型传入的)
     * 并且处理完成后自动向下传递 handler
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtil.randomId();
            loginResponsePacket.setUserId(userId);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    /**
     * 校验用户名密码
     * @param loginRequestPacket
     * @return 成功与否
     */
    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
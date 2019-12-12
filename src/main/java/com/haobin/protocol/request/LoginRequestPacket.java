/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.request;


import com.haobin.protocol.command.Command;
import com.haobin.protocol.Packet;
import lombok.Data;

/**
 * 登录请求指令
 * @author HaoBin
 * @version $Id: LoginRequestPacket.java, v0.1 2019/1/22 17:27 HaoBin
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;

    /**
     * @return 返回指令 登录请求
     */
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
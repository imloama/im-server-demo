/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.request;


import com.haobin.protocol.command.Command;
import com.haobin.protocol.Packet;

/**
 * @author HaoBin
 * @version $Id: LoginRequestPacket.java, v0.1 2019/1/22 17:27 HaoBin
 */
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
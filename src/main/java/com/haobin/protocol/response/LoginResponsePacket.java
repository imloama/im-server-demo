/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.response;

import com.haobin.protocol.command.Command;
import com.haobin.protocol.Packet;

/**
 *
 *
 * @author HaoBin
 * @version $Id: LoginResponsePacket.java, v0.1 2019/2/2 15:27 HaoBin 
 */
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
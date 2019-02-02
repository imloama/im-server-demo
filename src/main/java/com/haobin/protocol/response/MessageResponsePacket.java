/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;

/**
 *
 *
 * @author HaoBin
 * @version $Id: MessageResponsePacket.java, v0.1 2019/2/2 21:22 HaoBin 
 */
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
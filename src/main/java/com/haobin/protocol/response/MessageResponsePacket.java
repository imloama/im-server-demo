/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;

/**
 * @author HaoBin
 * @version $Id: MessageResponsePacket.java, v0.1 2019/2/2 21:22 HaoBin
 */
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromUserName;
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

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
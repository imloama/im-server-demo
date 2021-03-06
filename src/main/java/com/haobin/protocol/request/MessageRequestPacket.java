/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.request;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;

/**
 * @author HaoBin
 * @version $Id: MessageRequestPacket.java, v0.1 2019/2/2 21:21 HaoBin
 */
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;


    public MessageRequestPacket() {
    }

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
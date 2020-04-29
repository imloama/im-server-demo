package com.haobin.protocol.request;


import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

@Data
public class HeartBeatRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.HEARTBEAT_REQUEST;
    }
}

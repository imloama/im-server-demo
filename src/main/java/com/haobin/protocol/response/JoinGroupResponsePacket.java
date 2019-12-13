package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

/**
 * @Author HaoBin
 * @Create 2019/12/13 18:02
 * @Description: 加入群聊响应
 **/
@Data
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return Command.JOIN_GROUP_RESPONSE;
    }
}

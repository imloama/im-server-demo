package com.haobin.protocol.request;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

/**
 * @Author HaoBin
 * @Create 2019/12/13 17:55
 * @Description: 加入群聊请求
 **/
@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}

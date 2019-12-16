package com.haobin.protocol.request;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:19
 * @Description: 成员列表请求
 **/
@Data
public class ListGroupMembersRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}

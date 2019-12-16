package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import com.haobin.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:40
 * @Description:
 **/
@Data
public class ListGroupMemberResponsePacket extends Packet {


    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}

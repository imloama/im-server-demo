package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Author HaoBin
 * @Create 2019/12/13 16:32
 * @Description: 创建群聊响应
 **/
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}

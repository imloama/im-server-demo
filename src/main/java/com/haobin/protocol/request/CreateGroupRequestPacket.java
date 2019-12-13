package com.haobin.protocol.request;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

import java.util.List;

/**
 * @Author HaoBin
 * @Create 2019/12/13 16:17
 * @Description: 创建群聊
 **/
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}

package com.haobin.protocol.request;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:02
 * @Description: 退出群聊请求
 **/
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}

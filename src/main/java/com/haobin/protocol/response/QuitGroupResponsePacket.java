package com.haobin.protocol.response;

import com.haobin.protocol.Packet;
import com.haobin.protocol.command.Command;
import lombok.Data;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:08
 * @Description: 退出群聊响应
 **/
@Data
public class QuitGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}

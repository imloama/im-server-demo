package com.haobin.client.console.command;

import com.haobin.client.console.ConsoleCommand;
import com.haobin.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @Author HaoBin
 * @Create 2019/12/16 10:16
 * @Description: 列出成员列表命令
 **/
public class ListGroupMemberConsoleCommand implements ConsoleCommand {
    
    private Logger logger = LoggerFactory.getLogger(ListGroupMemberConsoleCommand.class);

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
        logger.info("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();
        listGroupMembersRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}

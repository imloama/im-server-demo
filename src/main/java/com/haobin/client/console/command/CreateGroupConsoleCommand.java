package com.haobin.client.console.command;

import com.haobin.client.console.ConsoleCommand;
import com.haobin.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Scanner;

/**h
 * @Author HaoBin
 * @Create 2019/12/13 16:16
 * @Description: 创建群聊命令指令
 **/
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private Logger logger = LoggerFactory.getLogger(CreateGroupConsoleCommand.class);

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        logger.info("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}

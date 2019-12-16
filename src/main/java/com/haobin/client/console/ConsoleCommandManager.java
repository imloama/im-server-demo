package com.haobin.client.console;

import com.haobin.client.console.command.*;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author HaoBin
 * @Create 2019/12/13 16:10
 * @Description: 管理控制台
 **/
public class ConsoleCommandManager implements ConsoleCommand {

    private Logger logger = LoggerFactory.getLogger(ConsoleCommandManager.class);

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        this.consoleCommandMap = new HashMap<>();
        this.consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        this.consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        this.consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        this.consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        this.consoleCommandMap.put("listGroupMembers", new ListGroupMemberConsoleCommand());
        this.consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand != null) {
            consoleCommand.exec(scanner, channel);
        } else {
            logger.error("无法识别指令[{}]", command);
        }
    }
}

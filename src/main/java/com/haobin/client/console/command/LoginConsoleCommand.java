package com.haobin.client.console.command;

import com.haobin.client.console.ConsoleCommand;
import com.haobin.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * @Author HaoBin
 * @Create 2019/12/13 16:21
 * @Description: 登录指令
 **/
public class LoginConsoleCommand implements ConsoleCommand {

    private Logger logger = LoggerFactory.getLogger(LoginConsoleCommand.class);


    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        logger.info("输入用户名登录: ");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}

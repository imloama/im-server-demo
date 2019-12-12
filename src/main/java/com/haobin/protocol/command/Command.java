/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.command;

/**
 * 指令常量
 * @author HaoBin
 * @version $Id: Command.java, v0.1 2019/1/22 17:44 HaoBin
 */
public interface Command {

    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登录响应
     */
    Byte LOGIN_RESPONSE = 2;

    /**
     * 发送消息请求
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 发送消息响应
     */
    Byte MESSAGE_RESPONSE = 4;
}

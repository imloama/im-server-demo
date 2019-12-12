/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol;

import lombok.Data;

/**
 * 通信协议格式
 *
 * @author HaoBin
 * @version $Id: Packet.java, v0.1 2019/1/22 17:41 HaoBin
 */
@Data
public abstract class Packet {

    /**
     * 版本号 1 字节
     */
    private Byte version = 1;

    /**
     * 指令 1 字节
     */
    public abstract Byte getCommand();
}
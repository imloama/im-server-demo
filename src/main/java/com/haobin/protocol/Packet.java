/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol;

/**
 * 通信包
 *
 * @author HaoBin
 * @version $Id: Packet.java, v0.1 2019/1/22 17:41 HaoBin
 */
public abstract class Packet {

    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();


    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}
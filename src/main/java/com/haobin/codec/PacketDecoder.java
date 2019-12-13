/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.codec;

import com.haobin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * 继承自 Netty ByteToMessageDecoder 二进制转java对象
 * 这个类会自动释放 ByteBuf 堆外内存
 * 详细见:
 * @author HaoBin
 * @version $Id: PacketDecoder.java, v0.1 2019/2/19 10:25 HaoBin
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
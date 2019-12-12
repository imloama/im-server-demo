/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol;

import com.haobin.protocol.command.Command;
import com.haobin.protocol.request.LoginRequestPacket;
import com.haobin.protocol.request.MessageRequestPacket;
import com.haobin.protocol.response.LoginResponsePacket;
import com.haobin.protocol.response.MessageResponsePacket;
import com.haobin.protocol.serialize.Serializer;
import com.haobin.protocol.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

/**
 * 通信格式编/解码 工具
 *
 * @author HaoBin
 * @version $Id: PacketCodeC.java, v0.1 2019/1/22 17:38 HaoBin
 */
public class PacketCodeC {
    // 魔数， 用来校验协议是否正确(4 字节)
    public static final int MAGIC_NUMBER = 0x12345678;
    // 单例实例
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    /**
     * 指令集合
     */
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;

    /**
     * 序列化算法集合
     */
    private final Map<Byte, Serializer> serializerMap;


    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }

    /**
     * 编码成通信格式
     * @param byteBuf ByteBuf
     * @param packet 消息
     */
    public void encode(ByteBuf byteBuf, Packet packet) {
        // 实际数据
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 4 字节的魔数
        byteBuf.writeInt(MAGIC_NUMBER);
        // 1 字节版本号
        byteBuf.writeByte(packet.getVersion());
        // 1 字节序列化算法
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 1 字节指令
        byteBuf.writeByte(packet.getCommand());
        // 4 字节数据长度
        byteBuf.writeInt(bytes.length);
        // 数据
        byteBuf.writeBytes(bytes);
    }


    /**
     * 解码成 java 对象
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        // 获取指令类型
        Class<? extends Packet> requestType = getRequestType(command);
        // 获取序列化算法
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }

}
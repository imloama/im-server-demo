/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.serialize;

import com.haobin.protocol.Packet;
import com.haobin.protocol.serialize.impl.JSONSerializer;

import java.util.regex.Pattern;

/**
 * @author HaoBin
 * @version $Id: Serializer.java, v0.1 2019/1/22 17:49 HaoBin
 */
public interface Serializer {

    /**
     * 默认是 json 序列化
     */
    Serializer DEFAULT = new JSONSerializer();

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;


    /**
     * 1 字节
     * @return 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转换成二进制
     * @param object 需要序列化的对象
     * @return 序列化的结果
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     * @param clazz class 类型
     * @param  bytes 字节数组
     * @return 反序列化的结果
     */
    <T extends Packet> T deserialize(Class<T> clazz, byte[] bytes);

}
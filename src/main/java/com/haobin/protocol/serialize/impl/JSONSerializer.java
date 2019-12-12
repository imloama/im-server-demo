/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.protocol.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.haobin.protocol.Packet;
import com.haobin.protocol.serialize.Serializer;
import com.haobin.protocol.serialize.SerializerAlgorithm;

/**
 * @author HaoBin
 * @version $Id: JSONSerializer.java, v0.1 2019/1/22 17:54 HaoBin
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return Serializer.JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T extends Packet> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
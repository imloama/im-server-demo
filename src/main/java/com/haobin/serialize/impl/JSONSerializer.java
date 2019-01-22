/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.haobin.serialize.Serializer;
import com.haobin.serialize.SerializerAlgorithm;

/**
 * @author HaoBin
 * @version $Id: JSONSerializer.java, v0.1 2019/1/22 17:54 HaoBin
 */
public class JSONSerializer implements Serializer {

    public byte getSerializerAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
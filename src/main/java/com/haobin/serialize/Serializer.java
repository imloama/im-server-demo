/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.serialize;

import com.haobin.serialize.impl.JSONSerializer;

/**
 * @author HaoBin
 * @version $Id: Serializer.java, v0.1 2019/1/22 17:49 HaoBin
 */
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.attribute;

import io.netty.util.AttributeKey;

/**
 * 判断是否登录标志位
 *
 * @author HaoBin
 * @version $Id: Attributes.java, v0.1 2019/2/2 21:23 HaoBin 
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}

/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.attribute;

import com.haobin.session.Session;
import io.netty.util.AttributeKey;

/**
 * 用户标识
 *
 * @author HaoBin
 * @version $Id: Attributes.java, v0.1 2019/2/2 21:23 HaoBin 
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}

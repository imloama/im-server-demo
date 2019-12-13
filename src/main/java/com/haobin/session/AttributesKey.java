/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.session;

import com.haobin.session.Session;
import io.netty.util.AttributeKey;

/**
 * channel Attribute 标识 session
 *
 * @author HaoBin
 * @version $Id: Attributes.java, v0.1 2019/2/2 21:23 HaoBin 
 */
public interface AttributesKey {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}

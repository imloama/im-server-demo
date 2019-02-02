/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.utils;

import com.haobin.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * 用来设置标志位和判断是否有标志位
 * @author HaoBin
 * @version $Id: LoginUtil.java, v0.1 2019/2/2 21:26 HaoBin
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
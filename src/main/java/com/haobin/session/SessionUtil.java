/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.session;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author HaoBin
 * @version $Id: SessionUtil.java, v0.1 2019/2/19 11:59 HaoBin
 */
public class SessionUtil {

    // userId -> channel 的映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    // groupId -> group 映射
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<>();

    /**
     * 绑定登录标识
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(AttributesKey.SESSION).set(session);
    }

    /**
     * 删除通道 session
     * @param channel
     */
    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(AttributesKey.SESSION).set(null);
        }
    }

    /**
     * 该通道是否有 session(是否登录)
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(AttributesKey.SESSION);
    }

    /**
     * 获取通道的 session
     * @param channel
     * @return
     */
    public static Session getSession(Channel channel) {
        return channel.attr(AttributesKey.SESSION).get();
    }

    /**
     * 获取用户的通道
     * @param userId
     */
    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }

    /**
     * 绑定 channelGroup
     * @param groupId
     * @param channelGroup
     */
    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        groupIdChannelGroupMap.put(groupId, channelGroup);
    }

    /**
     * 获取 channelGroup
     * @param groupId
     * @return
     */
    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdChannelGroupMap.get(groupId);
    }
}
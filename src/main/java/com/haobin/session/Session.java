/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.session;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Session.java, v0.1 2019/2/19 14:02 HaoBin 
 */
public class Session {
    private String userId;
    private String userName;

    public Session() {
    }

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 session
 *
 * @author HaoBin
 * @version $Id: Session.java, v0.1 2019/2/19 14:02 HaoBin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    private String userId;
    private String userName;
}
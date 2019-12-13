package com.haobin.utils;

import java.util.UUID;

/**
 * @Author HaoBin
 * @Create 2019/12/13 16:34
 * @Description: id 生成器
 **/
public class IDUtil {

    public static String randomId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}

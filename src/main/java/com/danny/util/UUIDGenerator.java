package com.danny.util;

import java.util.UUID;

/**
 * @author huyuyang@lxfintech.com
 * @Title: UUIDGenerator
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-20 14:48:43
 */
public class UUIDGenerator {
    public static String getUUID(){
        String uuid= UUID.randomUUID().toString();
        return uuid;
    }
}

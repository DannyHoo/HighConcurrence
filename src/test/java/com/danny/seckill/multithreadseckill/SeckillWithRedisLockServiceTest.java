package com.danny.seckill.multithreadseckill;

import org.codehaus.jackson.map.deser.ValueInstantiators;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithRedisLockServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-26 23:36:49
 */
public class SeckillWithRedisLockServiceTest extends BaseSeckillTest {
    public SeckillWithRedisLockServiceTest(String serviceName, long threadCount) {
        super(serviceName, threadCount);
    }

    public static void main(String[] args) {
        try {
            new SeckillWithRedisLockServiceTest("seckillWithRedisLockServiceImpl",5000).multiThreadSeckill();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

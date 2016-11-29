package com.danny.seckill.multithreadseckill;

import org.codehaus.jackson.map.deser.ValueInstantiators;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithReadWriteLockServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-24 00:28:33
 */
public class SeckillWithReadWriteLockServiceTest extends BaseSeckillTest {
    public SeckillWithReadWriteLockServiceTest(String serviceName, long threadCount) {
        super(serviceName, threadCount);
    }

    public static void main(String[] args) throws InterruptedException {
        new SeckillWithReadWriteLockServiceTest("seckillWithReadWriteLockServiceImpl",5000).multiThreadSeckill();
    }
}

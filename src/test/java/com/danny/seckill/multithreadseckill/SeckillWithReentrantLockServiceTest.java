package com.danny.seckill.multithreadseckill;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithReentrantLockServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-22 23:04:42
 */
public class SeckillWithReentrantLockServiceTest extends BaseSeckillTest {
    public SeckillWithReentrantLockServiceTest(String serviceName, long threadCount) {
        super(serviceName, threadCount);
    }

    public static void main(String[] args) throws InterruptedException {
        new SeckillWithReentrantLockServiceTest("seckillWithReentrantLockServiceImpl",5000).multiThreadSeckill();
    }
}

package com.danny.seckill.multithreadseckill;

import com.danny.seckill.service.SeckillService;
import com.danny.util.CommonResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithNoLockServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-22 23:11:43
 */
public class SeckillWithNoLockServiceTest extends BaseSeckillTest{

    public SeckillWithNoLockServiceTest(String serviceName, long threadCount) {
        super(serviceName, threadCount);
    }

    public static void main(String[] args) throws InterruptedException {
        new SeckillWithNoLockServiceTest("seckillWithNoLockServiceImpl",50).multiThreadSeckill();
    }
}

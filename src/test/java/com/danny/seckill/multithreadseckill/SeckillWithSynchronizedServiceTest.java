package com.danny.seckill.multithreadseckill;

import com.danny.seckill.service.SeckillService;
import com.danny.util.CommonResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithSynchronizedServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description: 并发秒杀
 * @Company: lxjr.com
 * @Created on 2016-11-21 23:46:11
 */
public class SeckillWithSynchronizedServiceTest extends BaseSeckillTest{

    public SeckillWithSynchronizedServiceTest(String serviceName, long threadCount) {
        super(serviceName, threadCount);
    }

    public static void main(String[] args) throws InterruptedException {
        new SeckillWithSynchronizedServiceTest("seckillWithSynchronizedServiceImpl",500).multiThreadSeckill();
    }
}


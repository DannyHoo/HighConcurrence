package com.danny.seckill.service;

import com.danny.seckill.multithreadseckill.SeckillWithSynchronizedServiceTest;
import com.danny.seckill.multithreadseckill.impl.SeckillWithSynchronizedServiceImpl;
import com.danny.seckill.service.impl.SeckillServiceImpl;
import com.danny.util.CommonResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-20 23:08:51
 */
public class SeckillServiceTest {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-*.xml"});

    public static void main(String[] args) throws InterruptedException {
        context.start();

        SeckillService seckillService=(SeckillService)context.getBean("seckillWithSynchronizedServiceImpl");
        String seckillItemId = "9ca835fa-ae76-11e6-94f7-05a017908d14";
        String userPhone = "11111111111";
        CommonResult result = seckillService.seckill(seckillItemId, userPhone);
        System.out.println("线程"+Thread.currentThread().getName()+"秒杀结果："+result.getMessage());
    }
}

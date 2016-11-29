package com.danny.seckill.multithreadseckill;

import com.danny.seckill.service.SeckillService;
import com.danny.util.CommonResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;

/**
 * @author huyuyang@lxfintech.com
 * @Title: BaseSeckillTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-22 23:07:47
 */
public class BaseSeckillTest {
    private String serviceName;//秒杀方式，传入相应的实现类名称，首字母小写（不加锁、synchronize、ReentrantLock、redis等方式）
    private long threadCount;//秒杀线程数量
    private SeckillService seckillService;
    private static ClassPathXmlApplicationContext context;
    private static long startTime;
    private static long endTime;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    {
        context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-*.xml"});
        context.start();
    }

    public BaseSeckillTest(String serviceName, long threadCount) {
        this.serviceName = serviceName;
        this.threadCount = threadCount;
    }

    public void multiThreadSeckill() throws InterruptedException {
        startTime=System.currentTimeMillis();
        System.out.println("***************************秒杀开始："+sdf.format(startTime)+"****************************");
        for (int i = 0; i < threadCount; i++) {
            BaseSeckillTest.SeckillThread seckillThread = new BaseSeckillTest.SeckillThread();
            seckillThread.start();
            seckillThread.join();
        }
        endTime=System.currentTimeMillis();
        System.out.println("***************************秒杀结束："+sdf.format(endTime)+" ****************************");
        System.out.println("***************************秒杀用时："+(endTime-startTime)+" ****************************");
    }

    class SeckillThread extends Thread {
        public void run() {
            seckillService = (SeckillService) context.getBean(serviceName);
            String seckillItemId = "9ca835fa-ae76-11e6-94f7-05a017908d14";
            String userPhone = "11111111111";
            CommonResult result = seckillService.seckill(seckillItemId, userPhone);
            System.out.println("线程" + Thread.currentThread().getName() + "秒杀结果：" + result.getMessage());
        }
    }
}

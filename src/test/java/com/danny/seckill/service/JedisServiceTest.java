package com.danny.seckill.service;

import com.danny.util.CommonResult;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author huyuyang@lxfintech.com
 * @Title: JedisServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-26 23:39:22
 */
public class JedisServiceTest {
    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/applicationContext-*.xml"});


    public static void main(String[] args) throws InterruptedException {
        context.start();
        JedisPool jedisPool=(JedisPool)context.getBean("redisClient");
        Jedis jedis=jedisPool.getResource();
        System.out.println(jedis.ping());
        jedis.set("flag","1");
        System.out.println(jedis.get("flag"));
        //System.out.println(jedis.decr("flag").intValue()>-1);
    }
}

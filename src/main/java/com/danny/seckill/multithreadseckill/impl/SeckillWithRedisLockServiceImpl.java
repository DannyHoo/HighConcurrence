package com.danny.seckill.multithreadseckill.impl;

import com.danny.seckill.dao.SeckillItemMapper;
import com.danny.seckill.dao.SeckillRecordMapper;
import com.danny.seckill.entity.SeckillItem;
import com.danny.seckill.entity.SeckillRecord;
import com.danny.seckill.multithreadseckill.SeckillWithRedisLockService;
import com.danny.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithRedisLockServiceImpl
 * @Copyright: Copyright (c) 2016
 * @Description: 分布式环境，不同jvm种对多线程就不好管理了，这时可以用分布式锁，like redis~
 * @Company: lxjr.com
 * @Created on 2016-11-26 23:23:30
 */
@Service
public class SeckillWithRedisLockServiceImpl implements SeckillWithRedisLockService {
    @Autowired
    private SeckillItemMapper seckillItemMapper;
    @Autowired
    private SeckillRecordMapper seckillRecordMapper;
    @Autowired
    private JedisPool jedisPool;

    public CommonResult seckill(String seckillItemId, String userPhone) {

        CommonResult result = new CommonResult();

        Jedis jedis = jedisPool.getResource();

        try {
            if(jedis.decr("flag").intValue()>-1) {
                /* 1、查询秒杀商品剩余数量*/
                SeckillItem seckillItem = seckillItemMapper.selectByPrimaryKey(seckillItemId);
                int itemAccount = seckillItem != null ? seckillItem.getNumber() : 0;
                if (itemAccount < 1) {
                    result.setStatus(false);
                    result.setMessage("商品【" + seckillItem.getName() + "】已经被抢光啦~");
                    return result;
                }

                /* 2、更新秒杀商品剩余数量*/
                seckillItem.setNumber(seckillItem.getNumber() - 1);
                seckillItemMapper.updateByPrimaryKey(seckillItem);

                /* 3、添加秒杀记录*/
                SeckillRecord seckillRecord = new SeckillRecord();
                seckillRecord.setSeckillItemId(seckillItemId);
                seckillRecord.setState(0);
                seckillRecord.setUserPhone(userPhone);
                seckillRecord.setCreateTime(new Date());
                seckillRecordMapper.insert(seckillRecord);
                result.setData(seckillItem);
                result.setMessage("恭喜您抢到商品【" + seckillItem.getName() + "】");
                jedis.incr("flag");
            }else{
                jedis.incr("flag");
                result.setStatus(false);
                result.setMessage("商品已经被抢光啦~");
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(false);
            result.setMessage("出了点小问题，我们的攻城狮正在奋力抢修~");
            return result;
        }finally{
            jedis.close();
        }
        return result;
    }
}

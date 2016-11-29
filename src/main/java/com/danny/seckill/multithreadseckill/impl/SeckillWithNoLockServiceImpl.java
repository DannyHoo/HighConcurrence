package com.danny.seckill.multithreadseckill.impl;

import com.danny.seckill.dao.SeckillItemMapper;
import com.danny.seckill.dao.SeckillRecordMapper;
import com.danny.seckill.entity.SeckillItem;
import com.danny.seckill.entity.SeckillRecord;
import com.danny.seckill.multithreadseckill.SeckillWithNoLockService;
import com.danny.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithNoLockServiceImpl
 * @Copyright: Copyright (c) 2016
 * @Description: 不加锁的情况下实现并发秒杀
 * @Company: lxjr.com
 * @Created on 2016-11-22 00:00:53
 */
@Service
public class SeckillWithNoLockServiceImpl implements SeckillWithNoLockService{
    @Autowired
    private SeckillItemMapper seckillItemMapper;
    @Autowired
    private SeckillRecordMapper seckillRecordMapper;

    public CommonResult seckill(String seckillItemId, String userPhone) {

        CommonResult result=new CommonResult();

        try {
        /* 1、查询秒杀商品剩余数量*/
            SeckillItem seckillItem = seckillItemMapper.selectByPrimaryKey(seckillItemId);
            int itemAccount = seckillItem != null ? seckillItem.getNumber() : 0;

        /* 2、更新秒杀商品剩余数量*/
            if (itemAccount < 1) {
                result.setStatus(false);
                result.setMessage("商品【" + seckillItem.getName() + "】已经被抢光啦~");
                return result;
            }
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
        }catch(Exception e){
            e.printStackTrace();
            result.setStatus(false);
            result.setMessage("出了点小问题，我们的攻城狮正在奋力抢修~");
            return result;
        }
        return result;
    }
}

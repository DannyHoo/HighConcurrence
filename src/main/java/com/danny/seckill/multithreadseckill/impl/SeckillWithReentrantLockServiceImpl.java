package com.danny.seckill.multithreadseckill.impl;

import com.danny.seckill.dao.SeckillItemMapper;
import com.danny.seckill.dao.SeckillRecordMapper;
import com.danny.seckill.entity.SeckillItem;
import com.danny.seckill.entity.SeckillRecord;
import com.danny.seckill.multithreadseckill.SeckillWithReentrantLockService;
import com.danny.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillWithReentrantLockServiceImpl
 * @Copyright: Copyright (c) 2016
 * @Description: 用重入锁实现多线程同步处理
 * 优点：灵活，可以在任何位置加锁、解锁，比如在catch到异常时可以手动释放锁，很优雅的控制了竞争资源的安全访问
 * 缺点：锁的只是代码块，没有读锁与写锁之分，相对于读写锁来说锁的粒度大，还有可以优化的空间
 * @Company: lxjr.com
 * @Created on 2016-11-22 22:50:44
 */
@Service
public class SeckillWithReentrantLockServiceImpl implements SeckillWithReentrantLockService {
    @Autowired
    private SeckillItemMapper seckillItemMapper;
    @Autowired
    private SeckillRecordMapper seckillRecordMapper;

    public CommonResult seckill(String seckillItemId, String userPhone) {

        CommonResult result = new CommonResult();
        Lock lock = new ReentrantLock();

        try {
            lock.lock();
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
            lock.unlock();
        } catch (Exception e) {
            lock.unlock();
            e.printStackTrace();
            result.setStatus(false);
            result.setMessage("出了点小问题，我们的攻城狮正在奋力抢修~");
            return result;
        }
        return result;
    }
}

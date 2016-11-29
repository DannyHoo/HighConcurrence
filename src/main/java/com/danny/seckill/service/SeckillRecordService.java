package com.danny.seckill.service;


import com.danny.seckill.entity.SeckillRecord;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillRecordService
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-19 21:17:27
 */
public interface SeckillRecordService {
    /**
     * 添加秒杀成功记录
     * @param seckillRecord
     * @return
     */
    int add(SeckillRecord seckillRecord);

    SeckillRecord findById(String id);
}

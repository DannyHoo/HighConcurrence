package com.danny.seckill.service.impl;

import com.danny.seckill.dao.SeckillRecordMapper;
import com.danny.seckill.entity.SeckillRecord;
import com.danny.seckill.service.SeckillRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillRecordServiceImpl
 * @Copyright: Copyright (c) 2016
 * @Description: 秒杀记录相关操作
 * @Company: lxjr.com
 * @Created on 2016-11-19 21:20:40
 */
@Service
public class SeckillRecordServiceImpl implements SeckillRecordService {
    @Autowired
    private SeckillRecordMapper seckillRecordMapper;

    public int add(SeckillRecord seckillRecord) {
        int result=seckillRecordMapper.insert(seckillRecord);
        int i=1/0;
        return result;
    }

    public SeckillRecord findById(String id){
        SeckillRecord seckillRecord=seckillRecordMapper.selectByPrimaryKey(id);
        return seckillRecord;
    }
}

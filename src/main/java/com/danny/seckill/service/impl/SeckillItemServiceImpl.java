package com.danny.seckill.service.impl;

import com.danny.seckill.dao.SeckillItemMapper;
import com.danny.seckill.entity.SeckillItem;
import com.danny.seckill.entity.SeckillItemExample;
import com.danny.seckill.service.SeckillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillItemServiceImpl
 * @Copyright: Copyright (c) 2016
 * @Description: 商品相关操作实现
 * @Company: lxjr.com
 * @Created on 2016-11-20 17:59:52
 */
@Service
public class SeckillItemServiceImpl implements SeckillItemService {
    @Autowired
    private SeckillItemMapper seckillItemMapper;

    /**
     * 根据主键查找秒杀商品
     * @param seckillItemId
     * @return
     */
    public SeckillItem findSeckillItemById(String seckillItemId) {
        SeckillItem seckillItem=seckillItemMapper.selectByPrimaryKey(seckillItemId);
        return seckillItem;
    }

    /**
     * 查找所有秒杀商品
     * @return
     */
    public List<SeckillItem> findAllSeckillItems() {
        return null;
    }

    /**
     * 更新秒杀商品
     * @param seckillItem
     * @return
     */
    public int updateSeckillItem(SeckillItem seckillItem) {
        int result=seckillItemMapper.updateByPrimaryKey(seckillItem);
        return result;
    }
}

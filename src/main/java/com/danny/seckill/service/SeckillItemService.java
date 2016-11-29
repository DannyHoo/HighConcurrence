package com.danny.seckill.service;

import com.danny.seckill.entity.SeckillItem;

import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillItemService
 * @Copyright: Copyright (c) 2016
 * @Description: 商品相关操作
 * @Company: lxjr.com
 * @Created on 2016-11-20 17:58:37
 */
public interface SeckillItemService {
    /**
     * 根据主键查找秒杀商品
     * @param seckillItemId
     * @return
     */
    public SeckillItem findSeckillItemById(String seckillItemId);

    /**
     * 查找所有秒杀商品
     * @return
     */
    public List<SeckillItem> findAllSeckillItems();

    /**
     * 更新秒杀商品
     * @param seckillItem
     * @return
     */
    public int updateSeckillItem(SeckillItem seckillItem);
}

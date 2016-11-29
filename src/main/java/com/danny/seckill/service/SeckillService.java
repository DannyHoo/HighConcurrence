package com.danny.seckill.service;
import com.danny.util.CommonResult;
/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillService
 * @Copyright: Copyright (c) 2016
 * @Description: 执行秒杀动作
 * @Company: lxjr.com
 * @Created on 2016-11-20 15:18:56
 */
public interface SeckillService {
    CommonResult seckill(String seckillItemId,String userPhone);
}

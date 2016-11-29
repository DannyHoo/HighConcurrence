package com.danny.seckill.controller;

import com.danny.seckill.entity.SeckillRecord;
import com.danny.seckill.service.SeckillService;
import com.danny.seckill.service.SeckillRecordService;
import com.danny.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: IndexController
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-20 12:39:06
 */
@Controller
public class IndexController {
    @Autowired
    private SeckillRecordService seckillRecordService;
    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/")
    public String index(ModelMap model) {
        System.out.println("dfadfadfads");
        SeckillRecord seckillRecord = seckillRecordService.findById("1005");
        model.addAttribute("userPhone", (seckillRecord != null ? seckillRecord.getUserPhone() : "查找失败"));
        System.out.println(model.get("userPhone"));
        return "index";
    }

    /**
     * 执行秒杀
     * @param model
     * @return
     */
    @RequestMapping("/seckill")
    public String seckill(ModelMap model) {
        String seckillItemId = "9ca835fa-ae76-11e6-94f7-05a017908d14";
        String userPhone = "15810354363";
        CommonResult result = seckillService.seckill(seckillItemId, userPhone);
        model.addAttribute("resultmessage", result.getMessage());
        System.out.println("秒杀结果："+result.getMessage());
        return "seckill/seckillresult";
    }

    /**
     * 添加秒杀记录（测试）
     * @param model
     * @return
     */
    @RequestMapping("/addSeckillRecord")
    public String addSeckillRecord(ModelMap model) {
        SeckillRecord seckillRecord = new SeckillRecord();
        seckillRecord.setSeckillItemId("9ca835fa-ae76-11e6-94f7-05a017908d14");
        seckillRecord.setUserPhone("18730644300");
        seckillRecord.setState(0);
        seckillRecord.setCreateTime(new Date());
        int result = seckillRecordService.add(seckillRecord);
        System.out.println("插入" + seckillRecord.getId());
        model.addAttribute("userPhone", (seckillRecord != null ? seckillRecord.getUserPhone() : "插入失败"));
        return "index";
    }
}

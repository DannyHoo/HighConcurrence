package com.danny.seckill.service;

import com.danny.seckill.dao.SeckillItemMapper;
import com.danny.seckill.entity.SeckillItem;
import com.danny.seckill.entity.SeckillRecord;
import com.danny.util.CommonResult;
import com.danny.util.UUIDGenerator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SeckillRecordServiceTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2016-11-19 21:26:04
 */
public class SeckillRecordServiceTest {
    private ClassPathXmlApplicationContext context;

    @Before
    public void init() {
        //context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/applicationContext-*.xml"});
        context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring/*.xml"});
        context.start();
    }

    @Test
    public void testAdd() {
        String uuid= UUIDGenerator.getUUID();
        System.out.println(uuid);

        SeckillRecord seckillRecord = new SeckillRecord();
        seckillRecord.setSeckillItemId("9ca835fa-ae76-11e6-94f7-05a017908d14");
        seckillRecord.setUserPhone("18730644380");
        seckillRecord.setState(0);
        seckillRecord.setCreateTime(new Date());
        SeckillRecordService seckillRecordService = (SeckillRecordService) context.getBean("seckillRecordServiceImpl");
        int result = seckillRecordService.add(seckillRecord);
        System.out.println("插入" + seckillRecord.getId());
    }

    @Test
    public void testFind() {
        SeckillRecordService seckillRecordService = (SeckillRecordService) context.getBean("seckillRecordServiceImpl");
        SeckillRecord seckillRecord = seckillRecordService.findById("1005");

        if (seckillRecord != null) {
            System.out.println(seckillRecord.getUserPhone());
        }
    }

    @Test
    public void testUpdateItem() {
        SeckillItemService seckillItemService=(SeckillItemService)context.getBean("seckillItemServiceImpl");
        SeckillItem seckillItem=seckillItemService.findSeckillItemById("9ca835fa-ae76-11e6-94f7-05a017908d14");
        System.out.println("number"+seckillItem.getNumber());
        seckillItem.setNumber(seckillItem.getNumber()-1);
        int result=seckillItemService.updateSeckillItem(seckillItem);
        System.out.println("result:"+result);
    }

    @Test
    public void testSeckill(){
        SeckillService seckillService=(SeckillService)context.getBean("seckillServiceImpl");
        String seckillItemId = "9ca835fa-ae76-11e6-94f7-05a017908d14";
        String userPhone = "11111111111";
        CommonResult result = seckillService.seckill(seckillItemId, userPhone);
        System.out.println("秒杀结果："+result.getMessage());

    }

    @Test
    public void testFinditem(){
        SeckillItemService seckillItemService=(SeckillItemService)context.getBean("seckillItemServiceImpl");
        SeckillItem seckillItem=seckillItemService.findSeckillItemById("9ca835fa-ae76-11e6-94f7-05a017908d14");
        System.out.println(seckillItem.getName());
    }
}

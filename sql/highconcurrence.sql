/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : localhost
 Source Database       : highconcurrence

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : utf-8

 Date: 11/20/2016 09:41:03 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `seckill_item`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_item`;
CREATE TABLE `seckill_item` (
  `id` varchar(36) NOT NULL DEFAULT '' COMMENT '商品库存id',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int(11) NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- ----------------------------
--  Records of `seckill_item`
-- ----------------------------
BEGIN;
INSERT INTO `seckill_item` VALUES ('1000', '1000元秒杀iPhone6s', '96', '2016-08-04 00:00:00', '2017-11-02 00:00:00', '2016-09-13 16:01:55'), ('1001', '500元秒杀ipad2', '100', '2016-08-05 00:00:00', '2015-11-02 00:00:00', '2016-08-04 18:54:34'), ('1002', '300元秒杀小米4', '100', '2016-08-05 00:00:00', '2015-11-02 00:00:00', '2016-08-04 18:54:36'), ('1003', '200元秒杀红米note', '100', '2016-08-05 00:00:00', '2015-11-02 00:00:00', '2016-08-04 18:54:38'), ('9ca835fa-ae76-11e6-94f7-05a017908d14', '1元秒杀可乐', '0', '2016-08-04 00:00:00', '2016-08-04 00:00:00', '2016-11-28 23:55:12');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
--  Table structure for `seckill_record`
-- ----------------------------
DROP TABLE IF EXISTS `seckill_record`;
CREATE TABLE `seckill_record` (
  `id` varchar(36) NOT NULL,
  `seckill_item_id` varchar(36) NOT NULL COMMENT '秒杀商品id',
  `user_phone` varchar(11) NOT NULL COMMENT '用户手机号',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态标示：-1：无效 0：成功 1：已付款 2：已发货 3：已收货 4：退款',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_create_time` (`create_time`),
  KEY `seckill_item_id` (`seckill_item_id`),
  CONSTRAINT `seckill_item_id` FOREIGN KEY (`seckill_item_id`) REFERENCES `seckill_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

SET FOREIGN_KEY_CHECKS = 1;
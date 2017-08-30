/*
Navicat MySQL Data Transfer

Source Server         : 192.168.0.21
Source Server Version : 50718
Source Host           : 192.168.0.21:3306
Source Database       : cmgs

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-08-30 21:37:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_brand
-- ----------------------------
DROP TABLE IF EXISTS `car_brand`;
CREATE TABLE `car_brand` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '品牌名称',
  `alpha_code` varchar(1) NOT NULL COMMENT '品牌首字母',
  `mark` varchar(50) NOT NULL COMMENT '品牌拼音',
  `icon` varchar(100) DEFAULT NULL COMMENT '品牌图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COMMENT='汽车品牌';

-- ----------------------------
-- Table structure for car_business
-- ----------------------------
DROP TABLE IF EXISTS `car_business`;
CREATE TABLE `car_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '商户名称',
  `avatar` varchar(100) DEFAULT NULL COMMENT '商家头像',
  `phone` varchar(30) NOT NULL COMMENT '电话',
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人',
  `prov` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) NOT NULL COMMENT '市',
  `district` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `imgUrl` text COMMENT '商家图片',
  `desc` text COMMENT '商家简介',
  `createTime` datetime DEFAULT NULL COMMENT '注册时间',
  `modifyTime` datetime DEFAULT NULL,
  `serviceTime` varchar(50) DEFAULT NULL COMMENT '服务时间',
  `isDeleted` int(11) DEFAULT '0',
  `latitude` decimal(10,8) DEFAULT NULL COMMENT '纬度',
  `longitude` decimal(11,8) DEFAULT NULL COMMENT '经度',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19394 DEFAULT CHARSET=utf8 COMMENT='商户';

-- ----------------------------
-- Table structure for car_model
-- ----------------------------
DROP TABLE IF EXISTS `car_model`;
CREATE TABLE `car_model` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `series_id` bigint(11) NOT NULL DEFAULT '0',
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '车型名称',
  `guide_price` decimal(10,2) DEFAULT NULL COMMENT '车型指导价',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片',
  `car_size` varchar(32) DEFAULT '' COMMENT '车身尺寸：长*宽*高(mm)',
  `car_doors` int(1) DEFAULT '0' COMMENT '车身结构：门数',
  `car_seats` int(1) DEFAULT '0' COMMENT '车身结构：座数',
  `displacement` decimal(10,2) DEFAULT '0.00' COMMENT '发动机：排量L',
  `car_horsepower` decimal(10,2) DEFAULT '0.00' COMMENT '发动机：马力',
  `car_engine_type` varchar(10) DEFAULT '' COMMENT '发动机：形式',
  `car_driving_way` varchar(32) DEFAULT '' COMMENT '驱动方式',
  `owner_fuel_consumption` decimal(10,2) DEFAULT '0.00' COMMENT '车主油耗：L/100km',
  `combined_fuel_consumption` decimal(10,2) DEFAULT '0.00' COMMENT '综合油耗：L/100km',
  `vehicle_warranty` varchar(32) DEFAULT '' COMMENT '整车质保',
  `transmission` varchar(32) DEFAULT '' COMMENT '变速箱',
  `owner_score` decimal(10,2) DEFAULT '0.00' COMMENT '车主评分：分',
  `is_turbo` tinyint(1) DEFAULT '0' COMMENT '是否涡轮增压',
  `car_value` decimal(10,2) DEFAULT '0.00' COMMENT '整备质量：kg',
  `front_tyre_size` varchar(64) DEFAULT NULL COMMENT '前轮胎规格',
  `rear_tyre_size` varchar(64) DEFAULT NULL COMMENT '后轮胎规格',
  `model_wash_type` tinyint(4) DEFAULT NULL COMMENT '车型洗车类型',
  `wash_type` tinyint(4) DEFAULT NULL COMMENT '大小车型 , 1小车 , 2 大车',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21208 DEFAULT CHARSET=utf8 COMMENT='汽车型号';

-- ----------------------------
-- Table structure for car_order
-- ----------------------------
DROP TABLE IF EXISTS `car_order`;
CREATE TABLE `car_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(2) DEFAULT '1' COMMENT '订单类型1买服务2买便宜券3买年卡',
  `businessId` int(2) DEFAULT '0' COMMENT '商户ID',
  `userId` int(11) DEFAULT NULL COMMENT '用户ID',
  `userPhone` varchar(20) DEFAULT NULL COMMENT '车主电话',
  `lv1ServiceType` int(11) DEFAULT '0' COMMENT '第一级服务id',
  `lv2ServiceType` int(11) DEFAULT '0' COMMENT '第二级服务id',
  `packageName` varchar(100) DEFAULT NULL COMMENT '套餐名称',
  `paidTime` datetime DEFAULT NULL COMMENT '订单支付时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '更改时间',
  `isDeleted` int(11) DEFAULT '0' COMMENT '是否已删除,1表示已删',
  PRIMARY KEY (`id`),
  KEY `order_userid_order_addtime` (`userId`),
  KEY `order_appointed_business_id_order_addtime` (`businessId`),
  KEY `index_name` (`lv1ServiceType`),
  KEY `bid_lv1_index` (`businessId`,`lv1ServiceType`),
  KEY `order_type` (`type`),
  KEY `is_delete_index` (`isDeleted`) USING BTREE,
  KEY `lv1_service_type_index` (`lv1ServiceType`) USING BTREE,
  KEY `care_shop_id_index` (`businessId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4832113 DEFAULT CHARSET=utf8 COMMENT='报价订单';

-- ----------------------------
-- Table structure for car_province
-- ----------------------------
DROP TABLE IF EXISTS `car_province`;
CREATE TABLE `car_province` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `query_violation_status` int(11) DEFAULT '0' COMMENT '是否支持违章查询',
  `has_cities` int(11) DEFAULT '1' COMMENT '是否有城市',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_series
-- ----------------------------
DROP TABLE IF EXISTS `car_series`;
CREATE TABLE `car_series` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `brand_id` bigint(11) NOT NULL DEFAULT '0',
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '车系名称',
  `mark` varchar(50) NOT NULL COMMENT '车系拼音',
  `guide_price_low` decimal(10,2) DEFAULT NULL COMMENT '指导价下限',
  `guide_price_high` decimal(10,2) DEFAULT NULL COMMENT '指导价上限',
  `classify` varchar(50) DEFAULT NULL COMMENT '车系级别',
  `wash_car_type` tinyint(4) DEFAULT '1' COMMENT '洗车类型：1普通，2SUV，3MPV',
  `wash_type` tinyint(4) DEFAULT NULL COMMENT '大小车型 , 1小车 , 2 大车',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1327 DEFAULT CHARSET=utf8 COMMENT='汽车系列';

-- ----------------------------
-- Table structure for car_service_type
-- ----------------------------
DROP TABLE IF EXISTS `car_service_type`;
CREATE TABLE `car_service_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `icon` varchar(100) DEFAULT NULL COMMENT '服务项目图标',
  `detail` text,
  `mark` varchar(100) DEFAULT NULL COMMENT '备注',
  `lv` tinyint(4) NOT NULL DEFAULT '2',
  `parent` int(11) DEFAULT '0',
  `isValid` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `is_valid` (`isValid`)
) ENGINE=InnoDB AUTO_INCREMENT=50028 DEFAULT CHARSET=utf8 COMMENT='商家服务类型';

-- ----------------------------
-- Table structure for car_type_brand
-- ----------------------------
DROP TABLE IF EXISTS `car_type_brand`;
CREATE TABLE `car_type_brand` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(20) NOT NULL COMMENT '品牌名称',
  `alpha_code` varchar(1) NOT NULL COMMENT '品牌首字母',
  `mark` varchar(50) NOT NULL COMMENT '品牌拼音',
  `icon` varchar(100) DEFAULT NULL COMMENT '品牌图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=225 DEFAULT CHARSET=utf8 COMMENT='汽车品牌';

-- ----------------------------
-- Table structure for car_type_model
-- ----------------------------
DROP TABLE IF EXISTS `car_type_model`;
CREATE TABLE `car_type_model` (
  `id` bigint(11) NOT NULL DEFAULT '0',
  `series_id` bigint(11) NOT NULL DEFAULT '0',
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '车型名称',
  `guide_price` decimal(10,2) DEFAULT NULL COMMENT '车型指导价',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片',
  `car_size` varchar(32) DEFAULT '' COMMENT '车身尺寸：长*宽*高(mm)',
  `car_doors` int(1) DEFAULT '0' COMMENT '车身结构：门数',
  `car_seats` int(1) DEFAULT '0' COMMENT '车身结构：座数',
  `displacement` decimal(10,2) DEFAULT '0.00' COMMENT '发动机：排量L',
  `car_horsepower` decimal(10,2) DEFAULT '0.00' COMMENT '发动机：马力',
  `car_engine_type` varchar(10) DEFAULT '' COMMENT '发动机：形式',
  `car_driving_way` varchar(32) DEFAULT '' COMMENT '驱动方式',
  `owner_fuel_consumption` decimal(10,2) DEFAULT '0.00' COMMENT '车主油耗：L/100km',
  `combined_fuel_consumption` decimal(10,2) DEFAULT '0.00' COMMENT '综合油耗：L/100km',
  `vehicle_warranty` varchar(32) DEFAULT '' COMMENT '整车质保',
  `transmission` varchar(32) DEFAULT '' COMMENT '变速箱',
  `owner_score` decimal(10,2) DEFAULT '0.00' COMMENT '车主评分：分',
  `is_turbo` tinyint(1) DEFAULT '0' COMMENT '是否涡轮增压',
  `car_value` decimal(10,2) DEFAULT '0.00' COMMENT '整备质量：kg',
  `front_tyre_size` varchar(64) DEFAULT NULL COMMENT '前轮胎规格',
  `rear_tyre_size` varchar(64) DEFAULT NULL COMMENT '后轮胎规格',
  `model_wash_type` tinyint(4) DEFAULT NULL COMMENT '车型洗车类型',
  `wash_type` tinyint(4) DEFAULT NULL COMMENT '大小车型 , 1小车 , 2 大车',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_type_series
-- ----------------------------
DROP TABLE IF EXISTS `car_type_series`;
CREATE TABLE `car_type_series` (
  `id` bigint(11) NOT NULL DEFAULT '0',
  `brand_id` bigint(11) NOT NULL DEFAULT '0',
  `auto_home_id` bigint(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '车系名称',
  `mark` varchar(50) NOT NULL COMMENT '车系拼音',
  `guide_price_low` decimal(10,2) DEFAULT NULL COMMENT '指导价下限',
  `guide_price_high` decimal(10,2) DEFAULT NULL COMMENT '指导价上限',
  `classify` varchar(50) DEFAULT NULL COMMENT '车系级别',
  `wash_car_type` tinyint(4) DEFAULT '1' COMMENT '洗车类型',
  `wash_type` tinyint(4) DEFAULT NULL COMMENT '大小车型 , 1小车 , 2 大车',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `is_valid` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_user
-- ----------------------------
DROP TABLE IF EXISTS `car_user`;
CREATE TABLE `car_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '个人用户ID',
  `phone` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '昵称',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像',
  `chatMail` varchar(32) DEFAULT '' COMMENT '聊天服务器域',
  `isValid` int(2) DEFAULT '1' COMMENT '是否有效',
  `createTime` datetime DEFAULT NULL COMMENT '注册时间',
  `modifyTime` datetime DEFAULT NULL COMMENT '登录时间',
  `deviceId` varchar(36) DEFAULT NULL COMMENT '设备标识',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `create_time` (`createTime`),
  KEY `login_time_upgrade_time` (`modifyTime`),
  KEY `device_id` (`deviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=4262 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Table structure for car_user_car
-- ----------------------------
DROP TABLE IF EXISTS `car_user_car`;
CREATE TABLE `car_user_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `carNum` varchar(10) NOT NULL COMMENT '车牌号',
  `brandId` int(2) DEFAULT NULL COMMENT '品牌',
  `modelId` int(2) DEFAULT NULL COMMENT '型号',
  `seriesId` int(4) DEFAULT NULL COMMENT '车系',
  `kilometers` decimal(10,0) DEFAULT NULL COMMENT '公里数',
  `vehicleFrameNo` varchar(30) DEFAULT NULL COMMENT '车架号',
  `engineNo` varchar(50) DEFAULT NULL COMMENT '发动机号',
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `modifyTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `isDefault` int(2) DEFAULT '1' COMMENT '1为默认',
  `isDeleted` tinyint(4) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `car_userid` (`userId`),
  KEY `car_brand` (`brandId`),
  KEY `car_model` (`modelId`),
  KEY `car_series` (`seriesId`),
  KEY `car_num` (`carNum`)
) ENGINE=InnoDB AUTO_INCREMENT=36450 DEFAULT CHARSET=utf8 COMMENT='我的车型';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `realName` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

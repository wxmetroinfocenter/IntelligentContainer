use intelligentcontainer;

DROP TABLE IF EXISTS `intelligent_container`;
DROP TABLE IF EXISTS `intelligent_container_box`;
 
CREATE TABLE `intelligent_container` (
  `id` varchar(64) DEFAULT NULL COMMENT '货柜内部识别码',
  `deviceId` varchar(100) DEFAULT NULL COMMENT '货柜设备编码',
  `deviceName` varchar(100) DEFAULT NULL COMMENT '货柜名',
  `status` varchar(32) DEFAULT NULL COMMENT '货柜状态',
  `token` varchar(100) DEFAULT NULL COMMENT '令牌',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '智能货柜表';
 
 CREATE TABLE `intelligent_container_box` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_no` varchar(64) DEFAULT NULL COMMENT '货柜内部识别码',
  `no` varchar(32) DEFAULT NULL COMMENT '货柜柜箱编号',
  `status` varchar(32) DEFAULT NULL COMMENT '货柜柜箱状态',
  `type` varchar(32) DEFAULT NULL COMMENT '货柜柜箱类型',
  `sort` int DEFAULT NULL COMMENT '排序',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '货柜柜箱表';

 CREATE TABLE `intelligent_container_box_opening` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icid` varchar(64) DEFAULT NULL COMMENT '货柜内部识别码',
  `no` varchar(32) DEFAULT NULL COMMENT '货柜柜箱编号',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '货柜柜箱表';
  

delete from sys_dict where type= 'ic_status';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_status_0', '0', '初始化', 'ic_status', '货柜状态', 10, '0','1',now(),'1',now()),
('ic_status_1', '1', '已注册', 'ic_status', '货柜状态', 20, '0','1',now(),'1',now()),
('ic_status_2', '2', '离线', 'ic_status', '货柜状态', 30, '0','1',now(),'1',now()),
('ic_status_3', '3', '冻结', 'ic_status', '货柜状态', 40, '0','1',now(),'1',now());
delete from sys_dict where type= 'ic_box_status';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_box_status_0', '0', '已锁', 'ic_box_status', '货柜柜箱状态', 10, '0','1',now(),'1',now()),
('ic_box_status_1', '1', '未锁', 'ic_box_status', '货柜柜箱状态', 20, '0','1',now(),'1',now()),
('ic_box_status_2', '2', '异常', 'ic_box_status', '货柜柜箱状态', 30, '0','1',now(),'1',now());

delete from sys_dict where type= 'ic_box_type';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_box_type_0', '0', '小箱', 'ic_box_type', '柜箱类型', 10, '0','1',now(),'1',now()),
('ic_box_type_1', '1', '中箱', 'ic_box_type', '柜箱类型', 20, '0','1',now(),'1',now()),
('ic_box_type_2', '2', '大箱', 'ic_box_type', '柜箱类型', 30, '0','1',now(),'1',now());
 
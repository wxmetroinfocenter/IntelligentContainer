use intelligentcontainer;

DROP TABLE IF EXISTS `intelligent_container`;
DROP TABLE IF EXISTS `intelligent_container_box`;
 
CREATE TABLE `intelligent_container` (
  `id` varchar(64) DEFAULT NULL COMMENT '�����ڲ�ʶ����',
  `deviceId` varchar(100) DEFAULT NULL COMMENT '�����豸����',
  `deviceName` varchar(100) DEFAULT NULL COMMENT '������',
  `status` varchar(32) DEFAULT NULL COMMENT '����״̬',
  `token` varchar(100) DEFAULT NULL COMMENT '����',
  `sort` int DEFAULT NULL COMMENT '����',
  `create_by` varchar(32) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `update_by` varchar(32) DEFAULT NULL COMMENT '������',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `remarks` varchar(500) DEFAULT NULL COMMENT '��ע��Ϣ',
  `del_flag` char(1) DEFAULT '0' COMMENT 'ɾ�����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '���ܻ����';
 
 CREATE TABLE `intelligent_container_box` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serial_no` varchar(64) DEFAULT NULL COMMENT '�����ڲ�ʶ����',
  `no` varchar(32) DEFAULT NULL COMMENT '���������',
  `status` varchar(32) DEFAULT NULL COMMENT '�������״̬',
  `type` varchar(32) DEFAULT NULL COMMENT '�����������',
  `sort` int DEFAULT NULL COMMENT '����',
  `create_by` varchar(32) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `update_by` varchar(32) DEFAULT NULL COMMENT '������',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `remarks` varchar(500) DEFAULT NULL COMMENT '��ע��Ϣ',
  `del_flag` char(1) DEFAULT '0' COMMENT 'ɾ�����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '��������';

 CREATE TABLE `intelligent_container_box_opening` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icid` varchar(64) DEFAULT NULL COMMENT '�����ڲ�ʶ����',
  `no` varchar(32) DEFAULT NULL COMMENT '���������',
  `create_by` varchar(32) DEFAULT NULL COMMENT '������',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `update_by` varchar(32) DEFAULT NULL COMMENT '������',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `remarks` varchar(500) DEFAULT NULL COMMENT '��ע��Ϣ',
  `del_flag` char(1) DEFAULT '0' COMMENT 'ɾ�����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT = '��������';
  

delete from sys_dict where type= 'ic_status';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_status_0', '0', '��ʼ��', 'ic_status', '����״̬', 10, '0','1',now(),'1',now()),
('ic_status_1', '1', '��ע��', 'ic_status', '����״̬', 20, '0','1',now(),'1',now()),
('ic_status_2', '2', '����', 'ic_status', '����״̬', 30, '0','1',now(),'1',now()),
('ic_status_3', '3', '����', 'ic_status', '����״̬', 40, '0','1',now(),'1',now());
delete from sys_dict where type= 'ic_box_status';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_box_status_0', '0', '����', 'ic_box_status', '�������״̬', 10, '0','1',now(),'1',now()),
('ic_box_status_1', '1', 'δ��', 'ic_box_status', '�������״̬', 20, '0','1',now(),'1',now()),
('ic_box_status_2', '2', '�쳣', 'ic_box_status', '�������״̬', 30, '0','1',now(),'1',now());

delete from sys_dict where type= 'ic_box_type';
INSERT INTO `sys_dict` (`id`, `value`, `label`, `type`, `description`, `sort`, `parent_id`, `create_by`,`create_date`,`update_by`,`update_date`) 
VALUES 
('ic_box_type_0', '0', 'С��', 'ic_box_type', '��������', 10, '0','1',now(),'1',now()),
('ic_box_type_1', '1', '����', 'ic_box_type', '��������', 20, '0','1',now(),'1',now()),
('ic_box_type_2', '2', '����', 'ic_box_type', '��������', 30, '0','1',now(),'1',now());
 
/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.dao;

import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainer;
import org.apache.ibatis.annotations.Param;

/**
 * 货柜管理DAO接口
 * @author huangzhengyu
 * @version 2019-11-26
 */
@MyBatisDao
public interface IntelligentContainerDao extends CrudDao<IntelligentContainer> {
    IntelligentContainer getBySerialNoAndToken(@Param("id") String id, @Param("token") String token);
}
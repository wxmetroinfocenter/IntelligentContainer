/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.dao;

import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBox;
import org.apache.ibatis.annotations.Param;

/**
 * 货柜管理DAO接口
 * @author huangzhengyu
 * @version 2019-11-26
 */
@MyBatisDao
public interface IntelligentContainerBoxDao extends CrudDao<IntelligentContainerBox> {

    IntelligentContainerBox getByKey(@Param("containerId") String containerId, @Param("boxNo") String boxNo);

}
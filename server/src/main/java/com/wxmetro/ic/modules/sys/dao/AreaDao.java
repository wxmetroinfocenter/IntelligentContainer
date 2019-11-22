/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.sys.dao;

import com.wxmetro.ic.common.persistence.TreeDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.sys.entity.Area;
import com.wxmetro.ic.common.persistence.TreeDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;

/**
 * 区域DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}

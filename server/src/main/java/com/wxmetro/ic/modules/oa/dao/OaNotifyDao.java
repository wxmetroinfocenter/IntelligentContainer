/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.oa.dao;

import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.oa.entity.OaNotify;
import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;

/**
 * 通知通告DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface OaNotifyDao extends CrudDao<OaNotify> {
	
	/**
	 * 获取通知数目
	 * @param oaNotify
	 * @return
	 */
	public Long findCount(OaNotify oaNotify);
	
}
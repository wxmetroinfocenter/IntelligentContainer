/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.sys.dao;

import java.util.List;

import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.sys.entity.Dict;
import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}

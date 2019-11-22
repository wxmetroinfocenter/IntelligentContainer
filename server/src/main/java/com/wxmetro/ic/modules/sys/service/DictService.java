/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.sys.service;

import java.util.List;

import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.common.utils.CacheUtils;
import com.wxmetro.ic.modules.sys.dao.DictDao;
import com.wxmetro.ic.modules.sys.entity.Dict;
import com.wxmetro.ic.modules.sys.utils.DictUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.common.utils.CacheUtils;
import com.wxmetro.ic.modules.sys.dao.DictDao;
import com.wxmetro.ic.modules.sys.entity.Dict;
import com.wxmetro.ic.modules.sys.utils.DictUtils;

/**
 * 字典Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CrudService<DictDao, Dict> {
	
	/**
	 * 查询字段类型列表
	 * @return
	 */
	public List<String> findTypeList(){
		return dao.findTypeList(new Dict());
	}

	@Transactional(readOnly = false)
	public void save(Dict dict) {
		super.save(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

	@Transactional(readOnly = false)
	public void delete(Dict dict) {
		super.delete(dict);
		CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
	}

}

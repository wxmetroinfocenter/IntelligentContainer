/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.sys.service;

import java.util.List;

import com.wxmetro.ic.common.service.TreeService;
import com.wxmetro.ic.modules.sys.dao.AreaDao;
import com.wxmetro.ic.modules.sys.entity.Area;
import com.wxmetro.ic.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.service.TreeService;
import com.wxmetro.ic.modules.sys.dao.AreaDao;
import com.wxmetro.ic.modules.sys.entity.Area;
import com.wxmetro.ic.modules.sys.utils.UserUtils;

/**
 * 区域Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {

	public List<Area> findAll(){
		return UserUtils.getAreaList();
	}

	@Transactional(readOnly = false)
	public void save(Area area) {
		super.save(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
	@Transactional(readOnly = false)
	public void delete(Area area) {
		super.delete(area);
		UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
	}
	
}

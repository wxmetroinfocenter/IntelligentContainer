/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.persistence.Page;
import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBoxOpening;
import com.wxmetro.ic.modules.icmgmt.dao.IntelligentContainerBoxOpeningDao;

/**
 * 柜箱开锁表Service
 * @author huanghzengyu
 * @version 2019-11-29
 */
@Service
@Transactional(readOnly = true)
public class IntelligentContainerBoxOpeningService extends CrudService<IntelligentContainerBoxOpeningDao, IntelligentContainerBoxOpening> {

	public IntelligentContainerBoxOpening get(String id) {
		return super.get(id);
	}
	
	public List<IntelligentContainerBoxOpening> findList(IntelligentContainerBoxOpening intelligentContainerBoxOpening) {
		return super.findList(intelligentContainerBoxOpening);
	}
	
	public Page<IntelligentContainerBoxOpening> findPage(Page<IntelligentContainerBoxOpening> page, IntelligentContainerBoxOpening intelligentContainerBoxOpening) {
		return super.findPage(page, intelligentContainerBoxOpening);
	}
	
	@Transactional(readOnly = false)
	public void save(IntelligentContainerBoxOpening intelligentContainerBoxOpening) {
		super.save(intelligentContainerBoxOpening);
	}
	
	@Transactional(readOnly = false)
	public void delete(IntelligentContainerBoxOpening intelligentContainerBoxOpening) {
		super.delete(intelligentContainerBoxOpening);
	}
	
}
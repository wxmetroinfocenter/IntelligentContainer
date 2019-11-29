/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.persistence.Page;
import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.common.utils.StringUtils;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainer;
import com.wxmetro.ic.modules.icmgmt.dao.IntelligentContainerDao;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBox;
import com.wxmetro.ic.modules.icmgmt.dao.IntelligentContainerBoxDao;

/**
 * 货柜管理Service
 * @author huangzhengyu
 * @version 2019-11-26
 */
@Service
@Transactional(readOnly = true)
public class IntelligentContainerService extends CrudService<IntelligentContainerDao, IntelligentContainer> {

	@Autowired
	private IntelligentContainerBoxDao intelligentContainerBoxDao;
	
	public IntelligentContainer get(String id) {
		IntelligentContainer intelligentContainer = super.get(id);
		if(intelligentContainer != null){
			intelligentContainer.setIntelligentContainerBoxList(intelligentContainerBoxDao.findList(new IntelligentContainerBox(intelligentContainer)));
		}
		return intelligentContainer;
	}

	public IntelligentContainer getBySerialNoAndToken(String id , String token) {
		IntelligentContainer intelligentContainer = this.dao.getBySerialNoAndToken(id, token);
		return intelligentContainer;
	}
	
	public List<IntelligentContainer> findList(IntelligentContainer intelligentContainer) {
		return super.findList(intelligentContainer);
	}
	
	public Page<IntelligentContainer> findPage(Page<IntelligentContainer> page, IntelligentContainer intelligentContainer) {
		return super.findPage(page, intelligentContainer);
	}
	
	@Transactional(readOnly = false)
	public void save(IntelligentContainer intelligentContainer) {
		this.saveWithDuplicateCheck(intelligentContainer);
		for (IntelligentContainerBox intelligentContainerBox : intelligentContainer.getIntelligentContainerBoxList()){
			if (intelligentContainerBox.getId() == null){
				continue;
			}
			if (IntelligentContainerBox.DEL_FLAG_NORMAL.equals(intelligentContainerBox.getDelFlag())){
				if (StringUtils.isBlank(intelligentContainerBox.getId())){
					intelligentContainerBox.setSerialNo(intelligentContainer);
					intelligentContainerBox.preInsert();
					intelligentContainerBoxDao.insert(intelligentContainerBox);
				}else{
					intelligentContainerBox.preUpdate();
					intelligentContainerBoxDao.update(intelligentContainerBox);
				}
			}else{
				intelligentContainerBoxDao.delete(intelligentContainerBox);
			}
		}
	}


	@Transactional(readOnly = false)
	public void saveBoxes(IntelligentContainer intelligentContainer) {
		for (IntelligentContainerBox intelligentContainerBox : intelligentContainer.getIntelligentContainerBoxList()){
			saveBox(intelligentContainerBox);
		}
	}

	@Transactional(readOnly = false)
	void saveBox(IntelligentContainerBox bean) {
		IntelligentContainerBox existed = intelligentContainerBoxDao.getByKey(bean.getSerialNo().getId() , bean.getNo());
		if(existed == null){
			bean.preInsert();
			intelligentContainerBoxDao.insert(bean);
		}else {
			existed.setStatus(bean.getStatus());
			existed.preUpdate();
			intelligentContainerBoxDao.update(existed);
		}
	}

	@Transactional(readOnly = false)
	public void saveWithDuplicateCheck(IntelligentContainer intelligentContainer) {
		IntelligentContainer existed = this.dao.get(intelligentContainer.getId());
		if(existed == null){
			intelligentContainer.preInsert();
			this.dao.insert(intelligentContainer);
		}else {
			BeanUtils.copyProperties(intelligentContainer, existed, "id");
			existed.preUpdate();
			this.dao.update(existed);
		}

	}

	@Transactional(readOnly = false)
	public void delete(IntelligentContainer intelligentContainer) {
		super.delete(intelligentContainer);
		intelligentContainerBoxDao.delete(new IntelligentContainerBox(intelligentContainer));
	}
	
}
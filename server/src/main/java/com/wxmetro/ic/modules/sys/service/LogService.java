/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved
 */
package com.wxmetro.ic.modules.sys.service;

import com.wxmetro.ic.common.persistence.Page;
import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.common.utils.DateUtils;
import com.wxmetro.ic.modules.sys.dao.LogDao;
import com.wxmetro.ic.modules.sys.entity.Log;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.persistence.Page;
import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.common.utils.DateUtils;
import com.wxmetro.ic.modules.sys.dao.LogDao;
import com.wxmetro.ic.modules.sys.entity.Log;

/**
 * 日志Service
 * @author ThinkGem
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

	public Page<Log> findPage(Page<Log> page, Log log) {
		
		// 设置默认时间范围，默认当前月
		if (log.getBeginDate() == null){
			log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
		}
		if (log.getEndDate() == null){
			log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
		}
		
		return super.findPage(page, log);
		
	}
	
}

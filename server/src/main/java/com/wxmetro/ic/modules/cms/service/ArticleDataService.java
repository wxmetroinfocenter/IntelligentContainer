/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.cms.service;

import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.modules.cms.dao.ArticleDataDao;
import com.wxmetro.ic.modules.cms.entity.ArticleData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxmetro.ic.common.service.CrudService;
import com.wxmetro.ic.modules.cms.dao.ArticleDataDao;
import com.wxmetro.ic.modules.cms.entity.ArticleData;

/**
 * 站点Service
 * @author ThinkGem
 * @version 2013-01-15
 */
@Service
@Transactional(readOnly = true)
public class ArticleDataService extends CrudService<ArticleDataDao, ArticleData> {

}

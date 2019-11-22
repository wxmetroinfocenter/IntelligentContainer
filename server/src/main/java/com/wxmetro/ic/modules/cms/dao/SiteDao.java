/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.cms.dao;

import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;
import com.wxmetro.ic.modules.cms.entity.Site;
import com.wxmetro.ic.common.persistence.CrudDao;
import com.wxmetro.ic.common.persistence.annotation.MyBatisDao;

/**
 * 站点DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@MyBatisDao
public interface SiteDao extends CrudDao<Site> {

}

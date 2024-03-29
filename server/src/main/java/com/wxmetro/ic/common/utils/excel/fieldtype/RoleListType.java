/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.common.utils.excel.fieldtype;

import java.util.List;

import com.google.common.collect.Lists;
import com.wxmetro.ic.common.utils.StringUtils;
import com.wxmetro.ic.common.utils.Collections3;
import com.wxmetro.ic.common.utils.SpringContextHolder;
import com.wxmetro.ic.modules.sys.entity.Role;
import com.wxmetro.ic.modules.sys.service.SystemService;
import com.wxmetro.ic.common.utils.Collections3;
import com.wxmetro.ic.common.utils.SpringContextHolder;
import com.wxmetro.ic.common.utils.StringUtils;

/**
 * 字段类型转换
 * @author ThinkGem
 * @version 2013-5-29
 */
public class RoleListType {

	private static SystemService systemService = SpringContextHolder.getBean(SystemService.class);
	
	/**
	 * 获取对象值（导入）
	 */
	public static Object getValue(String val) {
		List<Role> roleList = Lists.newArrayList();
		List<Role> allRoleList = systemService.findAllRole();
		for (String s : StringUtils.split(val, ",")){
			for (Role e : allRoleList){
				if (StringUtils.trimToEmpty(s).equals(e.getName())){
					roleList.add(e);
				}
			}
		}
		return roleList.size()>0?roleList:null;
	}

	/**
	 * 设置对象值（导出）
	 */
	public static String setValue(Object val) {
		if (val != null){
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>)val;
			return Collections3.extractToString(roleList, "name", ", ");
		}
		return "";
	}
	
}

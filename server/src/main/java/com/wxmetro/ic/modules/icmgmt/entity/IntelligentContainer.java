/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;
import com.google.common.collect.Lists;

import com.wxmetro.ic.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Range;

/**
 * 货柜管理Entity
 * @author huangzhengyu
 * @version 2019-11-26
 */
public class IntelligentContainer extends DataEntity<IntelligentContainer> {
	
	private static final long serialVersionUID = 1L;
	private String deviceId;		// 货柜设备编码
	private String deviceName;		// 货柜名
	private String token;		// 令牌
	private String sort;		// 排序
	private String status;      // 状态

	private int number = 1;
	private Date initDate; //初始化日期

	private List<IntelligentContainerBox> intelligentContainerBoxList = Lists.newArrayList();		// 子表列表
	
	public IntelligentContainer() {
		super();
	}

	public IntelligentContainer(String id){
		super(id);
	}

	@Length(min=0, max=100, message="货柜设备编码长度必须介于 0 和 100 之间")
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Length(min=0, max=100, message="货柜名长度必须介于 0 和 100 之间")
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	@Length(min=0, max=100, message="令牌长度必须介于 0 和 100 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Range(min=1,max=500 ,message="初始化货柜数量必须介于 1 和 500 之间")
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public List<IntelligentContainerBox> getIntelligentContainerBoxList() {
		return intelligentContainerBoxList;
	}

	public void setIntelligentContainerBoxList(List<IntelligentContainerBox> intelligentContainerBoxList) {
		this.intelligentContainerBoxList = intelligentContainerBoxList;
	}
}
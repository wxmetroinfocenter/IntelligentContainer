/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.entity;

import org.hibernate.validator.constraints.Length;

import com.wxmetro.ic.common.persistence.DataEntity;

/**
 * 货柜管理Entity
 * @author huangzhengyu
 * @version 2019-11-26
 */
public class IntelligentContainerBox extends DataEntity<IntelligentContainerBox> {
	
	private static final long serialVersionUID = 1L;
	private IntelligentContainer serialNo;		// 货柜内部识别码 父类
	private String no;		// 货柜柜箱编号
	private String status;		// 货柜柜箱状态
	private String sort;		// 排序
	
	public IntelligentContainerBox() {
		super();
	}

	public IntelligentContainerBox(String id){
		super(id);
	}

	public IntelligentContainerBox(IntelligentContainer serialNo){
		this.serialNo = serialNo;
	}

	@Length(min=0, max=32, message="货柜内部识别码长度必须介于 0 和 32 之间")
	public IntelligentContainer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(IntelligentContainer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=32, message="货柜柜箱编号长度必须介于 0 和 32 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	@Length(min=0, max=32, message="货柜柜箱状态长度必须介于 0 和 32 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="排序长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
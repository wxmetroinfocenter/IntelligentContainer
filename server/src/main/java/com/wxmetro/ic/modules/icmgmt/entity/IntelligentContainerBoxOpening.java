/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.entity;

import org.hibernate.validator.constraints.Length;

import com.wxmetro.ic.common.persistence.DataEntity;

/**
 * 柜箱开锁表Entity
 * @author huanghzengyu
 * @version 2019-11-29
 */
public class IntelligentContainerBoxOpening extends DataEntity<IntelligentContainerBoxOpening> {
	
	private static final long serialVersionUID = 1L;
	private String icid;		// 货柜内部识别码
	private String no;		// 货柜柜箱编号
	
	public IntelligentContainerBoxOpening() {
		super();
	}

	public IntelligentContainerBoxOpening(String id){
		super(id);
	}

	@Length(min=0, max=64, message="货柜内部识别码长度必须介于 0 和 64 之间")
	public String getIcid() {
		return icid;
	}

	public void setIcid(String icid) {
		this.icid = icid;
	}
	
	@Length(min=0, max=32, message="货柜柜箱编号长度必须介于 0 和 32 之间")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
}
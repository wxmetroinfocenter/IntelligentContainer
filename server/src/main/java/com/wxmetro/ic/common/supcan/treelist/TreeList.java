/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.common.supcan.treelist;

import java.util.List;

import com.google.common.collect.Lists;
import com.wxmetro.ic.common.supcan.annotation.common.fonts.SupFont;
import com.wxmetro.ic.common.supcan.annotation.treelist.SupTreeList;
import com.wxmetro.ic.common.supcan.common.Common;
import com.wxmetro.ic.common.supcan.common.fonts.Font;
import com.wxmetro.ic.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wxmetro.ic.common.supcan.common.Common;
import com.wxmetro.ic.common.supcan.common.fonts.Font;
import com.wxmetro.ic.common.supcan.common.properties.Properties;

/**
 * 硕正TreeList
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("TreeList")
public class TreeList extends Common {

	/**
	 * 列集合
	 */
	@XStreamAlias("Cols")
	private List<Object> cols;

	public TreeList() {
		super();
	}
	
	public TreeList(Properties properties) {
		this();
		this.properties = properties;
	}
	
	public TreeList(SupTreeList supTreeList) {
		this();
		if (supTreeList != null){
			if (supTreeList.properties() != null){
				this.properties = new Properties(supTreeList.properties());
			}
			if (supTreeList.fonts() != null){
				for (SupFont supFont : supTreeList.fonts()){
					if (this.fonts == null){
						this.fonts = Lists.newArrayList();
					}
					this.fonts.add(new Font(supFont));
				}
			}
		}
	}
	
	public List<Object> getCols() {
		if (cols == null){
			cols = Lists.newArrayList();
		}
		return cols;
	}

	public void setCols(List<Object> cols) {
		this.cols = cols;
	}

}

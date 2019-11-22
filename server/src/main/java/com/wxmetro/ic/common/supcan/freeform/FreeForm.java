/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.common.supcan.freeform;

import com.wxmetro.ic.common.supcan.common.Common;
import com.wxmetro.ic.common.supcan.common.properties.Properties;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wxmetro.ic.common.supcan.common.Common;
import com.wxmetro.ic.common.supcan.common.properties.Properties;

/**
 * 硕正FreeForm
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("FreeForm")
public class FreeForm extends Common {

	public FreeForm() {
		super();
	}
	
	public FreeForm(Properties properties) {
		this();
		this.properties = properties;
	}
	
}

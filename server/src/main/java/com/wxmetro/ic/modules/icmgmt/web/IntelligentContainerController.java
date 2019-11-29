/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxmetro.ic.common.utils.DateUtils;
import com.wxmetro.ic.common.utils.IdGen;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wxmetro.ic.common.config.Global;
import com.wxmetro.ic.common.persistence.Page;
import com.wxmetro.ic.common.web.BaseController;
import com.wxmetro.ic.common.utils.StringUtils;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainer;
import com.wxmetro.ic.modules.icmgmt.service.IntelligentContainerService;

import java.util.Date;

/**
 * 货柜管理Controller
 * @author huangzhengyu
 * @version 2019-11-26
 */
@Controller
@RequestMapping(value = "${adminPath}/icmgmt/intelligentContainer")
public class IntelligentContainerController extends BaseController {

	@Autowired
	private IntelligentContainerService intelligentContainerService;
	
	@ModelAttribute
	public IntelligentContainer get(@RequestParam(required=false) String id) {
		IntelligentContainer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = intelligentContainerService.get(id);
		}
		if (entity == null){
			entity = new IntelligentContainer();
		}
		return entity;
	}
	
	@RequiresPermissions("icmgmt:intelligentContainer:view")
	@RequestMapping(value = {"list", ""})
	public String list(IntelligentContainer intelligentContainer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<IntelligentContainer> page = intelligentContainerService.findPage(new Page<IntelligentContainer>(request, response), intelligentContainer); 
		model.addAttribute("page", page);
		return "modules/icmgmt/intelligentContainerList";
	}

	@RequiresPermissions("icmgmt:intelligentContainer:view")
	@RequestMapping(value = "form")
	public String form(IntelligentContainer intelligentContainer, Model model) {
		model.addAttribute("intelligentContainer", intelligentContainer);
		return "modules/icmgmt/intelligentContainerForm";
	}

	@RequiresPermissions("icmgmt:intelligentContainer:edit")
	@RequestMapping(value = "save")
	public String save(IntelligentContainer intelligentContainer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, intelligentContainer)){
			return form(intelligentContainer, model);
		}
		intelligentContainerService.save(intelligentContainer);
		addMessage(redirectAttributes, "保存智能货柜表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainer/?repage";
	}
	
	@RequiresPermissions("icmgmt:intelligentContainer:edit")
	@RequestMapping(value = "delete")
	public String delete(IntelligentContainer intelligentContainer, RedirectAttributes redirectAttributes) {
		intelligentContainerService.delete(intelligentContainer);
		addMessage(redirectAttributes, "删除智能货柜表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainer/?repage";
	}


	@RequiresPermissions("icmgmt:intelligentContainer:view")
	@RequestMapping(value = "initForm")
	public String initForm(IntelligentContainer intelligentContainer, Model model) {
		if(intelligentContainer.getInitDate() == null){
			intelligentContainer.setInitDate(new Date());
		}
		model.addAttribute("intelligentContainer", intelligentContainer);
		return "modules/icmgmt/intelligentContainerInit";
	}
	@RequiresPermissions("icmgmt:intelligentContainer:edit")
	@RequestMapping(value = "initSave")
	public String initSave(IntelligentContainer intelligentContainer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, intelligentContainer)){
			return initForm(intelligentContainer, model);
		}
		String today = DateUtils.formatDate(new Date(),"yyyyMMdd");
		for(int i = 0; i < intelligentContainer.getNumber();i++){
			intelligentContainer.setId(today + String.format("%03d", i+1) + "-" + IdGen.uuid());
			intelligentContainer.setStatus("0"); //初始化
			intelligentContainer.setToken(IdGen.randomBase62(16));
			intelligentContainerService.save(intelligentContainer);
		}

		addMessage(redirectAttributes, "保存智能货柜表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainer?repage";
	}

}
/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBoxOpening;
import com.wxmetro.ic.modules.icmgmt.service.IntelligentContainerBoxOpeningService;

/**
 * 柜箱开锁表Controller
 * @author huanghzengyu
 * @version 2019-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/icmgmt/intelligentContainerBoxOpening")
public class IntelligentContainerBoxOpeningController extends BaseController {

	@Autowired
	private IntelligentContainerBoxOpeningService intelligentContainerBoxOpeningService;
	
	@ModelAttribute
	public IntelligentContainerBoxOpening get(@RequestParam(required=false) String id) {
		IntelligentContainerBoxOpening entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = intelligentContainerBoxOpeningService.get(id);
		}
		if (entity == null){
			entity = new IntelligentContainerBoxOpening();
		}
		return entity;
	}
	
	@RequiresPermissions("icmgmt:intelligentContainerBoxOpening:view")
	@RequestMapping(value = {"list", ""})
	public String list(IntelligentContainerBoxOpening intelligentContainerBoxOpening, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<IntelligentContainerBoxOpening> page = intelligentContainerBoxOpeningService.findPage(new Page<IntelligentContainerBoxOpening>(request, response), intelligentContainerBoxOpening); 
		model.addAttribute("page", page);
		return "modules/icmgmt/intelligentContainerBoxOpeningList";
	}

	@RequiresPermissions("icmgmt:intelligentContainerBoxOpening:view")
	@RequestMapping(value = "form")
	public String form(IntelligentContainerBoxOpening intelligentContainerBoxOpening, Model model) {
		model.addAttribute("intelligentContainerBoxOpening", intelligentContainerBoxOpening);
		return "modules/icmgmt/intelligentContainerBoxOpeningForm";
	}

	@RequiresPermissions("icmgmt:intelligentContainerBoxOpening:edit")
	@RequestMapping(value = "save")
	public String save(IntelligentContainerBoxOpening intelligentContainerBoxOpening, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, intelligentContainerBoxOpening)){
			return form(intelligentContainerBoxOpening, model);
		}
		intelligentContainerBoxOpeningService.save(intelligentContainerBoxOpening);
		addMessage(redirectAttributes, "保存柜箱开锁表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainerBoxOpening/?repage";
	}
	
	@RequiresPermissions("icmgmt:intelligentContainerBoxOpening:edit")
	@RequestMapping(value = "delete")
	public String delete(IntelligentContainerBoxOpening intelligentContainerBoxOpening, RedirectAttributes redirectAttributes) {
		intelligentContainerBoxOpeningService.delete(intelligentContainerBoxOpening);
		addMessage(redirectAttributes, "删除柜箱开锁表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainerBoxOpening/?repage";
	}

}
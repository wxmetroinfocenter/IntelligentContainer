/**
 * Copyright &copy; 2020-2022 <a href="https://www.wxmetro.com/">wxmetro</a> All rights reserved.
 */
package com.wxmetro.ic.modules.icmgmt.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxmetro.ic.common.config.Constants;
import com.wxmetro.ic.common.utils.DateUtils;
import com.wxmetro.ic.common.utils.IdGen;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBox;
import com.wxmetro.ic.modules.icmgmt.entity.IntelligentContainerBoxOpening;
import com.wxmetro.ic.modules.icmgmt.service.IntelligentContainerBoxOpeningService;
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
import java.util.List;

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

	@Autowired
	private IntelligentContainerBoxOpeningService openingService;
	
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

	@RequiresPermissions("icmgmt:intelligentContainer:view")
	@RequestMapping(value = "boxlist")
	public String boxlist(IntelligentContainer intelligentContainer, Model model) {

		IntelligentContainer ic = intelligentContainerService.get(intelligentContainer.getId());
		model.addAttribute("intelligentContainer", ic);
		return "modules/icmgmt/intelligentContainerBoxList";
	}

	@RequiresPermissions("icmgmt:intelligentContainer:view")
	@RequestMapping(value = "openbox")
	public String openbox(IntelligentContainer intelligentContainer, Model model,RedirectAttributes redirectAttributes ) {
		logger.info("id " + intelligentContainer.getId() + ",box no : " + intelligentContainer.getNo() + ",boxStatus="
				+ intelligentContainer.getBoxStatus());

		if(Constants.IC_BOX_STATUS_UNLOCK.equals(intelligentContainer.getBoxStatus())){
			IntelligentContainer ic = intelligentContainerService.get(intelligentContainer.getId());

			IntelligentContainerBoxOpening boxOpeningCondition = new IntelligentContainerBoxOpening();
			boxOpeningCondition.setIcid(intelligentContainer.getId());

			List<IntelligentContainerBoxOpening> result =  openingService.findList(boxOpeningCondition);
			if(result != null && !result.isEmpty() ){
				logger.info("此货柜还有锁正待开锁");
				model.addAttribute("message","此货柜还有锁正待开锁，请稍后再试！");
				addMessage(redirectAttributes, "此货柜还有锁正待开锁，请稍后再试！");
				return "modules/icmgmt/intelligentContainerBoxList";
			}else {
				logger.info("加入开锁");

				IntelligentContainerBoxOpening boxOpening = new IntelligentContainerBoxOpening();
				boxOpening.setIcid(intelligentContainer.getId());
				boxOpening.setNo(intelligentContainer.getNo());
				openingService.save(boxOpening);

				//update box status
				IntelligentContainerBox box = new IntelligentContainerBox();
				box.setSerialNo(intelligentContainer);
				box.setNo(intelligentContainer.getNo());
				box.setStatus(Constants.IC_BOX_STATUS_UNLOCK);
				intelligentContainerService.saveBox(box);

				return boxlist(intelligentContainer, model);
			}

		}else {
			IntelligentContainerBox box = new IntelligentContainerBox();
			box.setSerialNo(intelligentContainer);
			box.setNo(intelligentContainer.getNo());
			box.setStatus(Constants.IC_BOX_STATUS_LOCK);
			intelligentContainerService.saveBox(box);

			return boxlist(intelligentContainer, model);
		}


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
			IntelligentContainer ic = new IntelligentContainer();
			ic.setId(today + String.format("%03d", i+1) + "-" + IdGen.uuid());
			ic.setStatus("0"); //初始化
			ic.setToken(IdGen.randomBase62(16));

			for(int j = 0 ; j < intelligentContainer.getBoxNum() ; j++){
				IntelligentContainerBox box = new IntelligentContainerBox();
				box.setNo(String.valueOf(j + 1));
				box.setStatus(Constants.IC_BOX_STATUS_LOCK);
				box.setType(Constants.IC_BOX_TYPE_SMALL);
				ic.getIntelligentContainerBoxList().add(box);
			}

			intelligentContainerService.save(ic);
		}

		addMessage(redirectAttributes, "保存智能货柜表成功");
		return "redirect:"+Global.getAdminPath()+"/icmgmt/intelligentContainer?repage";
	}

}
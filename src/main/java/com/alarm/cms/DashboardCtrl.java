package com.alarm.cms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardCtrl {
	
	/*
	 * 主页跳转
	 */
	@RequestMapping(value="/cms", method=RequestMethod.GET)
	public String index(@ModelAttribute("redirect") String redirect){
		
		return "redirect:/cms/dashboard/select";
	}
	
	@RequestMapping(value="/cms/dashboard/select", method=RequestMethod.GET)
	@ResponseBody
	public String select(@ModelAttribute("redirect") String redirect){
		
		return "DashboardView";
	}
}

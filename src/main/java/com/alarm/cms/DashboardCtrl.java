package com.alarm.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alarm.service.FuncService;

@Controller
@RequestMapping("/cms")
public class DashboardCtrl {
	
	@Autowired
	private FuncService funcService;
	/*
	 * 主页跳转
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(@ModelAttribute("redirect") String redirect){
		
		if( redirect != null ){
			return redirect;
		}
		
		return "redirect:/cms/dashboard/select";
	}
	
	@RequestMapping(value="/dashboard/select", method=RequestMethod.GET)
	public String select(@ModelAttribute("redirect") String redirect){
		
		if( redirect != null ){
			return redirect;
		}
		
		return "DashboardView";
	}
	
	@ModelAttribute
	public void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
}

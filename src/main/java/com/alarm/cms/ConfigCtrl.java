package com.alarm.cms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alarm.model.Config;
import com.alarm.model.User;
import com.alarm.service.ConfigService;
import com.alarm.service.FuncService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("cms/config")
public class ConfigCtrl {
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private FuncService funcService;
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(
			Model model, 
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		Config administrator = configService.selectByTitle("Administrator");
		JSONObject administratorObj = JSONObject.fromObject(administrator.getValue());
		model.addAttribute("username", administratorObj.getString("username"));
		
		return "ConfigView";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(
			@ModelAttribute("redirect") String redirect,
			@RequestParam("username") String username,
			@RequestParam("password") String password
	){
		if( redirect != null ){
			return redirect;
		}
		
		Config config = new Config();
		/*
		 * update administrator
		 */
		JSONObject administratorObj = new JSONObject();
		administratorObj.put("username", username);
		if( password == "" ){
			Config administrator = configService.selectByTitle("Administrator");
			JSONObject temp = JSONObject.fromObject(administrator.getValue());
			administratorObj.put("password", temp.getString("password"));
		}else{
			administratorObj.put("password", DigestUtils.md5Hex(password));
		}
		config.setTitle("Administrator");
		config.setValue(administratorObj.toString());
		configService.updateValueByTitle(config);
		
		return "redirect:/cms/config/update";
	}
	
	@ModelAttribute
	public void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
	
}
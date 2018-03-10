package com.alarm.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alarm.model.User;

@Service
public class FuncServiceImpl implements FuncService {

	public boolean isNumeric(String str) {
	    Pattern pattern = Pattern.compile("[0-9]*");
	    return pattern.matcher(str).matches();
	}
	
	public int getItemIndexOfArray(Object[] array , Object item) {
		List<Object> list = Arrays.asList(array);
		return list.indexOf(item);
	}
	
	public void modelAttribute(Model model, HttpSession httpSession, HttpServletRequest request){
		//判断是否登录
		User user = (User)httpSession.getAttribute("user");
		if( user == null ){
			model.addAttribute("redirect", "redirect:/cms/user/login");
			return;
		}else{
			model.addAttribute("redirect", null);
		}
		
		//分割url字符串,获取cms下标
		String[] urlArr = request.getRequestURL().toString().split("/");
		int cmsIndex = this.getItemIndexOfArray(urlArr, "cms");
		
		//解析出类名称
		String ctrl = urlArr[cmsIndex+1];
		model.addAttribute("classUpper", ctrl.substring(0, 1).toUpperCase() + ctrl.substring(1));
		model.addAttribute("classLower", ctrl);
		
		//解析出方法名称
		model.addAttribute("method", urlArr[cmsIndex+2]);
		
		//解析出排序名称和顺序
		int orderIndex = this.getItemIndexOfArray(urlArr, "order");
		if( orderIndex > -1 ){
			model.addAttribute("order", urlArr[orderIndex+1]);
		}
		int ascendIndex = this.getItemIndexOfArray(urlArr, "ascend");
		if( ascendIndex > -1 ){
			model.addAttribute("ascend", urlArr[ascendIndex+1]);
		}
		
		//当前登录用户名
		model.addAttribute("user_nickname", user.getNickname());
		
		//返回的url地址
		model.addAttribute("referer", request.getHeader("referer"));
	}
}

package com.alarm.cms;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alarm.model.Discuss;
import com.alarm.model.Expert;
import com.alarm.service.DiscussService;
import com.alarm.service.ExpertService;
import com.alarm.service.FuncService;

@Controller
@RequestMapping("cms/expert")
public class ExpertCtrl {
	
	@Autowired
	private FuncService funcService;
	
	@Autowired
	private ExpertService expertService;
	
	@Autowired
	private DiscussService discussService;
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String selectAll(){
		return "redirect:/cms/expert/select/1";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}", method=RequestMethod.GET)
	public String selectAll(
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend
	){
		return "redirect:/cms/expert/select/order/"+orderBy+"/ascend/"+ascend+"/1";
	}
	
	@RequestMapping(value="/select/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="page") Integer page
	){
		pager(model, page, "id", "desc");
		
		return "ExpertView";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend,
			@PathVariable(value="page") Integer page
	){
		pager(model, page, orderBy, ascend);
		
		return "ExpertView";
	}
	
	@RequestMapping(value="/insert/{discuss_id}", method=RequestMethod.GET)
	public String insert(
			Model model, 
			@PathVariable("discuss_id") Integer discuss_id
	){
		Expert expert = expertService.selectByDiscussId(discuss_id);
		if( expert != null ){
			return "redirect:/cms/expert/update/"+expert.getId();
		}
		Discuss discuss = discussService.selectByPrimaryKey(discuss_id);
		if( discuss != null ){
			expert = new Expert();
			expert.setDiscuss(discuss);
			model.addAttribute("expert", expert);
			return "ExpertView";
		}
		return "redirect:/cms/expert/select";
	}
	
	@RequestMapping(value="/insert/{discuss_id}", method=RequestMethod.POST)
	public String insert(
			Model model, 
			@PathVariable("discuss_id") Integer discuss_id,
			@ModelAttribute("expert") Expert expert,
			@RequestParam("referrer") String referrer
	){
		Discuss discuss = discussService.selectByPrimaryKey(discuss_id);
		if( discuss != null ){
			expert.setDiscuss(discuss);
			expert.setModifyDate(new Date());
			if( expertService.insert(expert) == 1 ){
				if( referrer != "" ){
					return "redirect:"+referrer.substring(referrer.lastIndexOf("/cms/"));
				}
				return "redirect:/cms/expert/select";
			}
		}
		
		return "ExpertView";
	}
	
	@RequestMapping(value="/update/{expert_id}", method=RequestMethod.GET)
	public String update(
			Model model, 
			@PathVariable("expert_id") Integer expert_id
	){
		Expert expert = expertService.selectByPrimaryKey(expert_id);
		if( expert != null ){
			model.addAttribute("expert", expert);
			return "ExpertView";
		}
		
		return "redirect:/cms/expert/select";
	}
	
	@RequestMapping(value="/update/{expert_id}", method=RequestMethod.POST)
	public String update(
			Model model, 
			@PathVariable("expert_id") Integer expert_id,
			@ModelAttribute("expert") Expert expert,
			@RequestParam("referrer") String referrer
	){
		expert.setId(expert_id);
		expert.setModifyDate(new Date());
		if( expertService.updateByPrimaryKey(expert) == 1 ){
			if( referrer != "" ){
				return "redirect:"+referrer.substring(referrer.lastIndexOf("/cms/"));
			}
			return "redirect:/cms/expert/select";
		}
		
		return "ExpertView";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			Model model, 
			@RequestParam("expert_id") Integer expert_id
	){
		Expert expert = expertService.selectByPrimaryKey(expert_id);
		if( expert != null ){
			expertService.deleteByPrimaryKey(expert);
		}
		
		return "redirect:/cms/expert/select";
	}
	
	private void pager(Model model, Integer page, String orderBy, String ascend){
		int pageSize = 20;
		long totalRecord = 0;
		totalRecord = expertService.selectCount();
		int totalPage = (int)Math.ceil((double)totalRecord/pageSize);
		
		if( page < 1 || page > totalPage ){
			page = 1;
		}
		
		Integer offset = (page-1)*pageSize;
		List<Expert> expert = expertService.selectAll(orderBy, ascend, offset, pageSize);
		
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("expert", expert);
	}
	
	@ModelAttribute
	private void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
}

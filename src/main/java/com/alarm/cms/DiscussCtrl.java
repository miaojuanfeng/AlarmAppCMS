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
import com.alarm.service.DiscussService;
import com.alarm.service.FuncService;

@Controller
@RequestMapping("cms/discuss")
public class DiscussCtrl {
	
	@Autowired
	private FuncService funcService;
	
	@Autowired
	private DiscussService discussService;
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String selectAll(){
		return "redirect:/cms/discuss/select/1";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}", method=RequestMethod.GET)
	public String selectAll(
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend
	){
		return "redirect:/cms/discuss/select/order/"+orderBy+"/ascend/"+ascend+"/1";
	}
	
	@RequestMapping(value="/select/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="page") Integer page
	){
		pager(model, page, "id", "desc");
		
		return "DiscussView";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend,
			@PathVariable(value="page") Integer page
	){
		pager(model, page, orderBy, ascend);
		
		return "DiscussView";
	}
	
	@RequestMapping(value="/update/{discuss_id}", method=RequestMethod.GET)
	public String update(
			Model model, 
			@PathVariable("discuss_id") Integer discuss_id
	){
		Discuss discuss = discussService.selectByPrimaryKey(discuss_id);
		if( discuss != null ){
			model.addAttribute("discuss", discuss);
			return "DiscussView";
		}
		
		return "redirect:/cms/discuss/select";
	}
	
	@RequestMapping(value="/update/{discuss_id}", method=RequestMethod.POST)
	public String update(
			Model model, 
			@PathVariable("discuss_id") Integer discuss_id,
			@ModelAttribute("discuss") Discuss discuss,
			@RequestParam("referrer") String referrer
	){
		discuss.setId(discuss_id);
		discuss.setModifyDate(new Date());
		if( discussService.updateByPrimaryKey(discuss) == 1 ){
			if( referrer != "" ){
				return "redirect:"+referrer.substring(referrer.lastIndexOf("/cms/"));
			}
			return "redirect:/cms/discuss/select";
		}
		
		return "DiscussView";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			Model model, 
			@RequestParam("discuss_id") Integer discuss_id
	){
		Discuss discuss = discussService.selectByPrimaryKey(discuss_id);
		if( discuss != null ){
			discussService.deleteByPrimaryKey(discuss);
		}
		
		return "redirect:/cms/discuss/select";
	}

	private void pager(Model model, Integer page, String orderBy, String ascend){
		int pageSize = 20;
		long totalRecord = 0;
		totalRecord = discussService.selectCount();
		int totalPage = (int)Math.ceil((double)totalRecord/pageSize);

		if( page < 1 || page > totalPage ){
			page = 1;
		}

		Integer offset = (page-1)*pageSize;
		List<Discuss> discuss = discussService.selectAll(orderBy, ascend, offset, pageSize);

		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("discuss", discuss);
	}
	
	@ModelAttribute
	private void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
}

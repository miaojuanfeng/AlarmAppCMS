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

import com.alarm.model.Comment;
import com.alarm.service.FuncService;
import com.alarm.service.CommentService;

@Controller
@RequestMapping("cms/comment")
public class CommentCtrl {
	
	@Autowired
	private FuncService funcService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String selectAll(){
		return "redirect:/cms/comment/select/1";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}", method=RequestMethod.GET)
	public String selectAll(
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend
	){
		return "redirect:/cms/comment/select/order/"+orderBy+"/ascend/"+ascend+"/1";
	}
	
	@RequestMapping(value="/select/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="page") Integer page,
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		pager(model, page, "id", "desc");
		
		return "CommentView";
	}
	
	@RequestMapping(value="/select/order/{orderBy}/ascend/{ascend}/{page}", method=RequestMethod.GET)
	public String selectAll(
			Model model,  
			@PathVariable(value="orderBy") String orderBy,
			@PathVariable(value="ascend") String ascend,
			@PathVariable(value="page") Integer page,
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		pager(model, page, orderBy, ascend);
		
		return "CommentView";
	}
	
	@RequestMapping(value="/update/{comment_id}", method=RequestMethod.GET)
	public String update(
			Model model, 
			@PathVariable("comment_id") Integer comment_id,
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		Comment comment = commentService.selectByPrimaryKey(comment_id);
		if( comment != null ){
			model.addAttribute("comment", comment);
			return "CommentView";
		}
		
		return "redirect:/cms/comment/select";
	}
	
	@RequestMapping(value="/update/{comment_id}", method=RequestMethod.POST)
	public String update(
			Model model, 
			@PathVariable("comment_id") Integer comment_id,
			@ModelAttribute("comment") Comment comment,
			@RequestParam("referrer") String referrer,
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		comment.setId(comment_id);
		comment.setModifyDate(new Date());
		if( commentService.updateByPrimaryKey(comment) == 1 ){
			if( referrer != "" ){
				return "redirect:"+referrer.substring(referrer.lastIndexOf("/cms/"));
			}
			return "redirect:/cms/comment/select";
		}
		
		return "CommentView";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			Model model, 
			@RequestParam("comment_id") Integer comment_id,
			@ModelAttribute("redirect") String redirect
	){
		if( redirect != null ){
			return redirect;
		}
		
		Comment comment = commentService.selectByPrimaryKey(comment_id);
		if( comment != null ){
			commentService.deleteByPrimaryKey(comment);
		}
		
		return "redirect:/cms/comment/select";
	}
	
	private void pager(Model model, Integer page, String orderBy, String ascend){
		int pageSize = 20;
		long totalRecord = 0;
		totalRecord = commentService.selectCount();
		int totalPage = (int)Math.ceil((double)totalRecord/pageSize);
		
		if( page < 1 || page > totalPage ){
			page = 1;
		}
		
		Integer offset = (page-1)*pageSize;
		List<Comment> comment = commentService.selectAll(orderBy, ascend, offset, pageSize);
		
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("comment", comment);
	}
	
	@ModelAttribute
	private void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
}

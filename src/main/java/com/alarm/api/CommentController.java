package com.alarm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.Comment;
import com.alarm.service.CommentService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	/**
	 * 添加评论
	 * @url ${base_url}/api/comment/insert
	 * @method POST
	 * @param Integer comment_discuss_id
	 * @param Integer comment_comment_id
	 * @param Integer comment_user_id
	 * @param String comment_content
	 * @return Json
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestParam("comment_discuss_id") Integer comment_discuss_id, @RequestParam("comment_comment_id") Integer comment_comment_id, @RequestParam("comment_user_id") Integer comment_user_id, @RequestParam("comment_content") String comment_content){
		JSONObject retval = new JSONObject();
		
		Comment comment = new Comment();
		comment.setDiscussId(comment_discuss_id);
		comment.setCommentId(comment_comment_id);
		comment.setUserId(comment_user_id);
		comment.setContent(comment_content);
		
		if( commentService.insert(comment) == 1 ){
			retval.put("status", true);
		}else{
			retval.put("status", false);
		}
		
		return retval.toString();
	}
}

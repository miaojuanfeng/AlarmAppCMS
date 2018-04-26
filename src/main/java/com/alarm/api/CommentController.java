package com.alarm.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.Comment;
import com.alarm.model.Discuss;
import com.alarm.model.User;
import com.alarm.service.CommentService;
import com.alarm.service.DiscussService;
import com.alarm.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="api/comment", produces="application/json;charset=UTF-8")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private DiscussService discussService;
	
	@Autowired
	private UserService userService;
	
//	/**
//	 * 获取评论详情
//	 * @URL ${base_url}/api/comment/select/discuss/{comment_discuss_id}
//	 * @method POST
//	 * @param Integer comment_discuss_id
//	 * @param String comment_order_by
//	 * @param String comment_ascend
//	 * @param Integer comment_offset
//	 * @param Integer comment_page_size
//	 * @return JSON
//	 */
//	@RequestMapping(value="/select/discuss/{comment_discuss_id}", method=RequestMethod.POST)
//	@ResponseBody
//	public String select(@PathVariable("comment_discuss_id") Integer comment_discuss_id,
//						@RequestParam(value="comment_order_by", required=false) String comment_order_by,
//						@RequestParam(value="comment_ascend", required=false) String comment_ascend,
//						@RequestParam("comment_offset") Integer comment_offset,
//						@RequestParam("comment_page_size") Integer comment_page_size){
//		JSONObject retval = new JSONObject();
//		JSONArray temp = new JSONArray();
//		
//		List<Comment> comment = commentService.selectByDiscussId(comment_discuss_id, comment_order_by, comment_ascend, comment_offset, comment_page_size);
//		
//		for( Comment c : comment ){
//			JSONObject t = new JSONObject();
//			t.put("id", c.getId());
//			t.put("discuss_title", c.getDiscuss().getTitle());
//			t.put("comment_user", c.getComment().getUser().getNickname());
//			t.put("content", c.getContent());
//			t.put("user_nickname", c.getUser().getNickname());
//			t.put("create_date", c.getCreateDate().getTime());
//			temp.add(t);
//		}
//		
//		retval.put("status", true);
//		retval.put("data", temp);
//		
//		return retval.toString();
//	}
	
	/**
	 * 添加评论
	 * @URL ${base_url}/api/comment/insert
	 * @method POST
	 * @param Integer comment_discuss_id
	 * @param Integer comment_comment_id
	 * @param Integer comment_user_id
	 * @param String comment_content
	 * @return JSON
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestParam("comment_discuss_id") Integer comment_discuss_id, @RequestParam("comment_comment_id") Integer comment_comment_id, @RequestParam("comment_user_id") Integer comment_user_id, @RequestParam("comment_content") String comment_content){
		JSONObject retval = new JSONObject();
		
		Comment comment = new Comment();
		
		Discuss discuss = discussService.selectByPrimaryKey(comment_discuss_id);
		if( discuss.getUser().getId() != comment_user_id ){
			userService.increUnread(discuss.getUser());
		}
		
		Comment replyComment = null;
		if( comment_comment_id > 0 ){
			replyComment = commentService.selectByPrimaryKey(comment_comment_id);
			if( replyComment.getUser().getId() != comment_user_id ){
				userService.increUnread(replyComment.getUser());
			}
		}
		
		User user = new User();
		user.setId(comment_user_id);
		
		comment.setDiscuss(discuss);
		comment.setComment(replyComment);
		comment.setUser(user);
		comment.setContent(comment_content);
		
		if( commentService.insert(comment) == 1 ){
			retval.put("status", true);
		}else{
			retval.put("status", false);
		}
		
		return retval.toString();
	}
}

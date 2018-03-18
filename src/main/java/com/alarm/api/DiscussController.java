package com.alarm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.Discuss;
import com.alarm.service.DiscussService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/discuss")
public class DiscussController {
	
	@Autowired
	private DiscussService discussService;
	
	/**
	 * 创建新话题
	 * @url ${base_url}/api/discuss/subject
	 * @param String discuss_title
	 * @param String discuss_content
	 * @param Integer discuss_user_id
	 * @return Json
	 */
	@RequestMapping(value="/subject", method=RequestMethod.POST)
	@ResponseBody
	public String subject(@RequestParam("discuss_title") String discuss_title, @RequestParam("discuss_content") String discuss_content, @RequestParam("discuss_user_id") Integer discuss_user_id){
		JSONObject retval = new JSONObject();
		
		Discuss discuss = new Discuss();
		discuss.setTitle(discuss_title);
		discuss.setContent(discuss_content);
		discuss.setUserId(discuss_user_id);
		
		if( discussService.insert(discuss) == 1 ){
			retval.put("status", true);
		}else{
			retval.put("status", false);
		}
		
		return retval.toString();
	}
	
	/**
	 * 添加评论
	 * @url ${base_url}/api/discuss/comment
	 * @param Integer comment_discuss_id
	 * @param Integer comment_comment_id
	 * @param Integer comment_user_id
	 * @param String comment_content
	 * @return Json
	 */
	@RequestMapping(value="/comment", method=RequestMethod.POST)
	@ResponseBody
	public String comment(@RequestParam("comment_discuss_id") Integer comment_discuss_id, @RequestParam("comment_comment_id") Integer comment_comment_id, @RequestParam("comment_user_id") Integer comment_user_id, @RequestParam("comment_content") String comment_content){
		JSONObject retval = new JSONObject();
		
		return retval.toString();
	}
}

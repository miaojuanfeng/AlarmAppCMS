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
import com.alarm.service.DiscussService;
import com.alarm.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="api/discuss", produces="application/json;charset=UTF-8")
public class DiscussController {
	
	@Autowired
	private DiscussService discussService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取所有话题
	 * @URL ${base_url}/api/discuss/select/all
	 * @method POST
	 * @param String discuss_order_by
	 * @param String discuss_ascend
	 * @param Integer discuss_offset
	 * @param Integer discuss_page_size
	 * @return JSON
	 */
	@RequestMapping(value="/select/all", method=RequestMethod.POST)
	@ResponseBody
	public String select(@RequestParam(value="discuss_order_by", required=false, defaultValue="id") String discuss_order_by,
						@RequestParam(value="discuss_ascend", required=false, defaultValue="desc") String discuss_ascend,
						@RequestParam("discuss_offset") Integer discuss_offset,
						@RequestParam("discuss_page_size") Integer discuss_page_size){
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		
		List<Discuss> discusses = discussService.selectAll(discuss_order_by, discuss_ascend, discuss_offset, discuss_page_size);
	
		for( Discuss discuss : discusses ){
			JSONObject t = new JSONObject();
			t.put("id", discuss.getId());
			t.put("title", discuss.getTitle());
			t.put("content", discuss.getContent());
			t.put("user_nickname", discuss.getUser().getNickname());
			t.put("create_date", discuss.getCreateDate().getTime()/1000);
			temp.add(t);
		}
		
		retval.put("status", true);
		retval.put("data", temp);
		
		return retval.toString();
	}
	
	/**
	 * 获取与用户有关的话题
	 * @URL ${base_url}/api/discuss/select/user/{discuss_user_id}
	 * @method POST
	 * @param Integer discuss_user_id
	 * @param String discuss_order_by
	 * @param String discuss_ascend
	 * @param Integer discuss_offset
	 * @param Integer discuss_page_size
	 * @return JSON
	 */
	@RequestMapping(value="/select/user/{discuss_user_id}", method=RequestMethod.POST)
	@ResponseBody
	public String select(@PathVariable("discuss_user_id") Integer discuss_user_id,
						@RequestParam(value="discuss_order_by", required=false, defaultValue="id") String discuss_order_by,
						@RequestParam(value="discuss_ascend", required=false, defaultValue="desc") String discuss_ascend,
						@RequestParam("discuss_offset") Integer discuss_offset,
						@RequestParam("discuss_page_size") Integer discuss_page_size){
		JSONObject retval = new JSONObject();
		JSONArray temp = new JSONArray();
		
		List<Discuss> discusses = discussService.selectByUser(discuss_user_id, discuss_order_by, discuss_ascend, discuss_offset, discuss_page_size);
	
		for( Discuss discuss : discusses ){
			JSONObject t = new JSONObject();
			t.put("id", discuss.getId());
			t.put("title", discuss.getTitle());
			t.put("content", discuss.getContent());
			t.put("user_nickname", discuss.getUser().getNickname());
			t.put("create_date", discuss.getCreateDate().getTime()/1000);
			temp.add(t);
		}
		
		User user = new User();
		user.setId(discuss_user_id);
		userService.clearUnread(user);
		
		retval.put("status", true);
		retval.put("data", temp);
		
		return retval.toString();
	}
	
	/**
	 * 获取话题详情
	 * @URL ${base_url}/api/discuss/select/detail/{discuss_id}
	 * @method POST
	 * @param Integer discuss_id
	 * @return JSON
	 */
	@RequestMapping(value="/select/detail/{discuss_id}", method=RequestMethod.POST)
	@ResponseBody
	public String select(@PathVariable("discuss_id") Integer discuss_id){
		JSONObject retval = new JSONObject();
		JSONObject temp = new JSONObject();

		Discuss discuss = discussService.selectByPrimaryKey(discuss_id);
	
		if( discuss != null ){
			temp.put("id", discuss.getId());
			temp.put("title", discuss.getTitle());
			temp.put("content", discuss.getContent());
			JSONObject e = new JSONObject();
			if( discuss.getExpert() != null ){
				e.put("id", discuss.getExpert().getId());
				e.put("content", discuss.getExpert().getContent());
				e.put("create_date", discuss.getExpert().getCreateDate().getTime()/1000);
			}
			temp.put("expert", e);
			JSONArray t = new JSONArray();
			if( discuss.getComment() != null ){
				for(Comment comment : discuss.getComment()){
					if( comment != null ){
						JSONObject c = new JSONObject();
						c.put("id", comment.getId());
						c.put("user_nickname", comment.getUser().getNickname());
						if( comment.getComment() != null ){
							c.put("reply_to_nickname", comment.getComment().getUser().getNickname());
						}else{
							c.put("reply_to_nickname", "");
						}
						c.put("content", comment.getContent());
						c.put("create_date", comment.getCreateDate().getTime()/1000);
						t.add(c);
					}
				}
			}
			temp.put("comment", t);
			temp.put("user_nickname", discuss.getUser().getNickname());
			temp.put("create_date", discuss.getCreateDate().getTime()/1000);
		}
		
		retval.put("status", true);
		retval.put("data", temp);
		
		return retval.toString();
	}
	
	/**
	 * 创建新话题
	 * @URL ${base_url}/api/discuss/insert
	 * @method POST
	 * @param String discuss_title
	 * @param String discuss_content
	 * @param Integer discuss_user_id
	 * @return JSON
	 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestParam("discuss_title") String discuss_title, @RequestParam("discuss_content") String discuss_content, @RequestParam("discuss_user_id") Integer discuss_user_id){
		JSONObject retval = new JSONObject();
		
		Discuss discuss = new Discuss();
		discuss.setTitle(discuss_title);
		discuss.setContent(discuss_content);
		User user = new User();
		user.setId(discuss_user_id);
		discuss.setUser(user);
		
		if( discussService.insert(discuss) == 1 ){
			retval.put("status", true);
		}else{
			retval.put("status", false);
		}
		
		return retval.toString();
	}
}

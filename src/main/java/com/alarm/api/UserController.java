package com.alarm.api;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.User;
import com.alarm.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @url ${base_url}/api/user/signup
	 * @method POST
	 * @param String user_username
	 * @param String user_password
	 * @return  Json {
	 * 				status
	 * 				data.user_id
					data.user_username
					data.user_number
					data.user_nickname
					data.user_platform
				} 
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public String signup(@RequestParam("user_username") String user_username, @RequestParam("user_password") String user_password){
		JSONObject retval = new JSONObject();
		
		User user = userService.selectByUsername(user_username);
		if( user == null ){
			user_password = DigestUtils.md5Hex(user_password);
	        
			user = new User();
			user.setUsername(user_username);
			user.setPassword(user_password);
			user.setNickname(user_username);
			int user_number = 0;
			while(true){
				user_number = (int)((Math.random()*9+1)*1000000000);
				User exist_user = userService.selectByNumber(user_number);
				if( exist_user == null ){
					break;
				}
			}
			user.setNumber(user_number);
			user.setPlatform("ios");
			user.setCreateDate(new Date());
			user.setModifyDate(new Date());
			user.setDeleted(0);
			if( userService.insert(user) == 1 ){
				JSONObject uJson = new JSONObject();
				uJson.put("user_id", user.getId());
				uJson.put("user_username", user.getUsername());
				uJson.put("user_number", user.getNumber());
				uJson.put("user_nickname", user.getNickname());
				uJson.put("user_platform", user.getPlatform());
				
				retval.put("status", true);
				retval.put("data", uJson);
			}else{
				retval.put("status", false);
				retval.put("msg", "User registration failed, please try again later");
			}
		}else{
			retval.put("status", false);
			retval.put("msg", "The username has been registered");
		}
		
		return retval.toString();
	}
	
	/**
	 * 用户登录
	 * @url ${base_url}/api/user/signin
	 * @method POST
	 * @param String user_username
	 * @param String user_password
	 * @return  Json {
	 * 				status
	 * 				data.user_id
					data.user_username
					data.user_number
					data.user_nickname
					data.user_platform
				}
	 */
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	@ResponseBody
	public String signin(@RequestParam("user_username") String user_username, @RequestParam("user_password") String user_password){
		JSONObject retval = new JSONObject();
		
		User user = userService.selectByUsername(user_username);
		if( user != null ){
			if( DigestUtils.md5Hex(user_password).equals(user.getPassword()) ){
				JSONObject uJson = new JSONObject();
				uJson.put("user_id", user.getId());
				uJson.put("user_username", user.getUsername());
				uJson.put("user_number", user.getNumber());
				uJson.put("user_nickname", user.getNickname());
				uJson.put("user_platform", user.getPlatform());
				
				retval.put("status", true);
				retval.put("data", uJson);
			}else{
				retval.put("status", false);
				retval.put("msg", "Incorrect user password");
			}
		}else{
			retval.put("status", false);
			retval.put("msg", "Unable to find the user");
 		}
 		
 		return retval.toString();
	}
	
	/**
	 * 更新用户昵称
	 * @url ${base_url}/api/user/update_nickname
	 * @method POST
	 * @param Integer user_id
	 * @param String user_nickname
	 * @return Json
	 */
	@RequestMapping(value="/update_nickname", method=RequestMethod.POST)
	@ResponseBody
	public String update_nickname(@RequestParam("user_id") Integer user_id, @RequestParam("user_nickname") String user_nickname){
		JSONObject retval = new JSONObject();
		
		
		return retval.toString();
	}
	
	/**
	 * 更新用户密码
	 * @url ${base_url}/api/user/update_password
	 * @method POST
	 * @param user_id
	 * @param user_password
	 * @return Json
	 */
	@RequestMapping(value="/update_password", method=RequestMethod.POST)
	@ResponseBody
	public String update_password(@RequestParam("user_id") Integer user_id, @RequestParam("user_password") String user_password){
		JSONObject retval = new JSONObject();
		
		
		return retval.toString();
	}
}

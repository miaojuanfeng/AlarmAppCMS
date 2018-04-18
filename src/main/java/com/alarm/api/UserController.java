package com.alarm.api;

import java.util.Date;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.User;
import com.alarm.service.CommentService;
import com.alarm.service.UserService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="api/user", produces="application/json;charset=UTF-8")
public class UserController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * 获取验证码
	 * @param user_username
	 * @param user_platform
	 * @return
	 */
	@RequestMapping(value="/verify", method=RequestMethod.POST)
	@ResponseBody
	public String verify(@RequestParam("user_username") String user_username, @RequestParam("user_platform") String user_platform){
		JSONObject retval = new JSONObject();
		
		int code = (int)((Math.random()*4+1)*1000);
		String verifyCode = DigestUtils.md5Hex(user_platform+"!mM$*9"+user_username+"Rd#s&D2"+code);
		
		//初始化clnt,使用单例方式
		YunpianClient clnt = new YunpianClient("c3f0061016033114e07424c7531020d5").init();
		
		//发送短信API
		Map<String, String> param = clnt.newParam(2);
		param.put(YunpianClient.MOBILE, user_username);
		param.put(YunpianClient.TEXT, "【運動鬧鐘】您的驗證碼是"+code+"。如非本人操作，請忽略本短信");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		
		logger.info(r.getCode());
		logger.info(r.getMsg());
		logger.info(r.getClass());
		logger.info(r.getDetail());
		logger.info(r.getData());
		logger.info(r.getThrowable());
		
		clnt.close();
		
		retval.put("status", true);
		
		JSONObject temp = new JSONObject();
		temp.put("verify_code", verifyCode);
		retval.put("data", temp);
		
		return retval.toString();
	}
	
	/**
	 * 用户注册
	 * @URL ${base_url}/api/user/signup
	 * @method POST
	 * @param String user_username
	 * @param String user_password
	 * @param String user_platform
	 * @return JSON
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public String signup(@RequestParam("user_username") String user_username, @RequestParam("user_password") String user_password, @RequestParam("user_platform") String user_platform){
		JSONObject retval = new JSONObject();
		
		User user = userService.selectByUsername(user_username);
		if( user == null ){
//			user_password = DigestUtils.md5Hex(user_password);
	        
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
			user.setPlatform(user_platform);
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
	 * @URL ${base_url}/api/user/signin
	 * @method POST
	 * @param String user_username
	 * @param String user_password
	 * @return JSON
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
	 * @URL ${base_url}/api/user/update_nickname
	 * @method POST
	 * @param Integer user_id
	 * @param String user_nickname
	 * @return JSON
	 */
	@RequestMapping(value="/update_nickname", method=RequestMethod.POST)
	@ResponseBody
	public String update_nickname(@RequestParam("user_id") Integer user_id, @RequestParam("user_nickname") String user_nickname){
		JSONObject retval = new JSONObject();
		
		
		return retval.toString();
	}
	
	/**
	 * 更新用户密码
	 * @URL ${base_url}/api/user/update_password
	 * @method POST
	 * @param user_id
	 * @param user_password
	 * @return JSON
	 */
	@RequestMapping(value="/update_password", method=RequestMethod.POST)
	@ResponseBody
	public String update_password(@RequestParam("user_id") Integer user_id, @RequestParam("user_password") String user_password){
		JSONObject retval = new JSONObject();
		
		
		return retval.toString();
	}
}

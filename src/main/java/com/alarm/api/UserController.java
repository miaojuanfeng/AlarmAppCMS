package com.alarm.api;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alarm.model.Star;
import com.alarm.model.User;
import com.alarm.service.FuncService;
import com.alarm.service.StarService;
import com.alarm.service.UserService;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="api/user", produces="application/json;charset=UTF-8")
public class UserController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;

	@Autowired
	private StarService starService;
	
	@Autowired
	private FuncService funcService;
	
	/**
	 * 获取验证码
	 * @URL ${base_url}/api/user/verify
	 * @method POST
	 * @param String user_username
	 * @param String user_platform
	 * @return JSON
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
		param.put(YunpianClient.TEXT, "【運動鬧鐘】您的驗證碼是"+code+"。如非本人操作，請忽略本短信。");
		Result<SmsSingleSend> r = clnt.sms().single_send(param);
		
		if( r.getCode() != 0 ){
			logger.info("Code: "+r.getCode()+" Detail:"+r.getDetail());
			
			retval.put("status", false);
			retval.put("msg", r.getDetail());
		}else{
			retval.put("status", true);
			
			JSONObject temp = new JSONObject();
			temp.put("verify_code", verifyCode);
			retval.put("data", temp);
		}
//		logger.info(r.getMsg());
//		logger.info(r.getClass());
//		logger.info(r.getDetail());
//		logger.info(r.getData());
//		logger.info(r.getThrowable());
		
		clnt.close();
		
		return retval.toString();
	}
	
	/**
	 * 用户注册
	 * @URL ${base_url}/api/user/signup
	 * @method POST
	 * @param String user_username
	 * @param String user_password
	 * @param String user_nickname
	 * @param String user_platform
	 * @return JSON
	 */
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public String signup(@RequestParam("user_username") String user_username, @RequestParam("user_password") String user_password, @RequestParam("user_nickname") String user_nickname, @RequestParam("user_platform") String user_platform){
		JSONObject retval = new JSONObject();
		
		User user = userService.selectByUsername(user_username);
		if( user == null ){
//			user_password = DigestUtils.md5Hex(user_password);
	        
			user = new User();
			user.setUsername(user_username);
			user.setPassword(DigestUtils.md5Hex(user_password));
			user.setNickname(user_nickname);
			user.setPlatform(user_platform);
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
				uJson.put("user_unread", user.getUnread());
				uJson.put("user_data", user.getData());
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
	
//	/**
//	 * 更新用户昵称
//	 * @URL ${base_url}/api/user/update_nickname
//	 * @method POST
//	 * @param Integer user_id
//	 * @param String user_nickname
//	 * @return JSON
//	 */
//	@RequestMapping(value="/update_nickname", method=RequestMethod.POST)
//	@ResponseBody
//	public String update_nickname(@RequestParam("user_id") Integer user_id, @RequestParam("user_nickname") String user_nickname){
//		JSONObject retval = new JSONObject();
//		
//		
//		return retval.toString();
//	}
	
	/**
	 * 更新用户密码
	 * @URL ${base_url}/api/user/update_password
	 * @method POST
	 * @param Integer user_id
	 * @param String user_old_password
	 * @param String user_password
	 * @return JSON
	 */
	@RequestMapping(value="/update_password", method=RequestMethod.POST)
	@ResponseBody
	public String update_password(@RequestParam("user_id") Integer user_id, @RequestParam("user_old_password") String user_old_password, @RequestParam("user_password") String user_password){
		JSONObject retval = new JSONObject();
		
		User user = userService.selectByPrimaryKey(user_id);
		if( user != null ){
			if( user.getPassword().equals(DigestUtils.md5Hex(user_old_password)) ){
				user.setPassword(DigestUtils.md5Hex(user_password));
				if( userService.updateByPrimaryKey(user) == 1 ){
					retval.put("status", true);
				}else{
					retval.put("status", false);
					retval.put("msg", "User password update failed, please try again later");
				}
			}else{
				retval.put("status", false);
				retval.put("msg", "Wrong old password");
			}
		}else{
			retval.put("status", false);
			retval.put("msg", "Can not find the user by user_id");
		}
		
		return retval.toString();
	}
	
	/**
	 * 获取星星排名
	 * @URL ${base_url}/api/user/user_star
	 * @method POST
	 * @param Integer user_id
	 * @return JSON
	 */
	@RequestMapping(value="/user_star", method=RequestMethod.POST)
	@ResponseBody
	public String user_star(@RequestParam("user_id") Integer user_id){
		JSONObject retval = new JSONObject();
		
		List<Star> stars = starService.selectAllByWeek();
		JSONArray starArr = new JSONArray();
		int userNo = 0;
		for(int i=0; i<stars.size(); i++){
			Star star = stars.get(i);
			JSONObject t = new JSONObject();
			t.put("id", star.getId());
			t.put("user_nickname", star.getUser().getNickname());
			t.put("star_num", star.getStarNum());
			t.put("like_num", star.getLikeNum());
			String userIds = star.getLikeUser();
			if( !userIds.equals("") && funcService.getItemIndexOfArray(userIds.split(","), String.valueOf(user_id)) != -1 ){
				t.put("is_like", true);
			}else{
				t.put("is_like", false);
			}
			starArr.add(t);
			if( star.getUser().getId() == user_id ){
				userNo = i+1;
				break;
			}
		}
		
		JSONObject data = new JSONObject();
		data.put("user_no", userNo);
		data.put("star_total", starArr.size());
		data.put("star_list", starArr);
		
		retval.put("status", true);
		retval.put("data", data);
		return retval.toString();
	}
	
	/**
	 * 用户点击赞
	 * @URL ${base_url}/api/user/user_like
	 * @method POST
	 * @param Integer star_id
	 * @param Integer user_id
	 * @return JSON
	 */
	@RequestMapping(value="/user_like", method=RequestMethod.POST)
	@ResponseBody
	public String user_like(@RequestParam("star_id") Integer star_id, @RequestParam("user_id") Integer user_id){
		JSONObject retval = new JSONObject();
		
		Star star = starService.selectByPrimaryKey(star_id);
		if( star != null ){
			String userIds = star.getLikeUser();
			// do Unlike
			JSONObject t = new JSONObject();
			if( !userIds.equals("") && funcService.getItemIndexOfArray(userIds.split(","), String.valueOf(user_id)) != -1 ){
				List<String> list = new LinkedList<String>();
				for(String s : Arrays.asList(userIds.split(","))){
					list.add(s);
				}
				list.remove(String.valueOf(user_id));
				String[] user = list.toArray(new String[0]);
				star.setLikeUser(String.join(",", user));
				star.setLikeNum(star.getLikeNum()-1);
				
				t.put("is_like", false);
			}else{
				List<String> list = new LinkedList<String>();
				for(String s : Arrays.asList(userIds.split(","))){
					list.add(s);
				}
				list.add(String.valueOf(user_id));
				String[] user = list.toArray(new String[0]);
				star.setLikeUser(String.join(",", user));
				star.setLikeNum(star.getLikeNum()+1);
				
				t.put("is_like", true);
			}
			if( starService.updateByPrimaryKey(star) == 1 ){
				retval.put("status", true);
				retval.put("data", t);
			}
		}else{
			retval.put("status", false);
			retval.put("msg", "找不到該用戶");
		}
		
		return retval.toString();
	}
	
	/**
	 * 上传用户星星
	 * @URL ${base_url}/api/user/upload_star
	 * @method POST
	 * @param Integer user_id
	 * @param Integer star_num
	 * @return JSON
	 */
	@RequestMapping(value="/upload_star", method=RequestMethod.POST)
	@ResponseBody
	public String upload_star(@RequestParam("user_id") Integer user_id, @RequestParam("star_num") Integer star_num){
		JSONObject retval = new JSONObject();
		
		Star star = starService.selectByUserId(user_id);
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		if( star != null ){
			if( week != star.getWeek() ){
				star.setWeek(week);
				star.setLikeNum(0);
				star.setLikeUser("");
			}
			star.setStarNum(star_num);
			if( starService.updateByPrimaryKey(star) == 1 ){
				retval.put("status", true);
			}
		}else{
			star = new Star();
			User user = new User();
			user.setId(user_id);
			
			star.setUser(user);
			star.setWeek(week);
			star.setLikeNum(0);
			star.setLikeUser("");
			star.setStarNum(star_num);
			if( starService.insert(star) == 1 ){
				retval.put("status", true);
			}
		}
		
		return retval.toString();
	}
}

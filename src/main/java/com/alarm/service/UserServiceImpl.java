package com.alarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.UserDao;
import com.alarm.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDao userDao;

	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectByPrimaryKey(id);
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		return userDao.selectCount();
	}

	public List<User> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return userDao.selectAll(orderBy, ascend, offset, pageSize);
	}

	public int updateByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		user.setModifyDate(new Date());
		return userDao.updateByPrimaryKey(user);
	}

	public int insert(User user) {
		// TODO Auto-generated method stub
		int user_number = 0;
		while(true){
			user_number = (int)((Math.random()*9+1)*1000000000);
			User exist_user = this.selectByNumber(user_number);
			if( exist_user == null ){
				break;
			}
		}
		user.setNumber(user_number);
		Date date = new Date();
		user.setCreateDate(date);
		user.setModifyDate(date);
		user.setDeleted(0);
		return userDao.insert(user);
	}

	public int deleteByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		user.setModifyDate(new Date());
		user.setDeleted(1);
		return userDao.deleteByPrimaryKey(user);
	}

	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.selectByUsername(username);
	}

	public User selectByNumber(Integer number) {
		// TODO Auto-generated method stub
		return userDao.selectByNumber(number);
	}

	public int increUnread(User user) {
		// TODO Auto-generated method stub
		return userDao.increUnread(user);
	}

	public int clearUnread(User user) {
		// TODO Auto-generated method stub
		return userDao.clearUnread(user);
	}

}

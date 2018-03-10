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
		user.setPlatform("ios");
		Date date = new Date();
		user.setCreateDate(date);
		user.setModifyDate(date);
		user.setDeleted(0);
		return userDao.insert(user);
	}

	public int deleteByPrimaryKey(User user) {
		// TODO Auto-generated method stub
		user.setModifyDate(new Date());
		return userDao.deleteByPrimaryKey(user);
	}

}

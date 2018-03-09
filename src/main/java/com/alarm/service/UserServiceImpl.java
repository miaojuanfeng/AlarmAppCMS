package com.alarm.service;

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
		return userDao.updateByPrimaryKey(user);
	}

}

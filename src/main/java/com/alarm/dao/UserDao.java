package com.alarm.dao;

import java.util.List;

import com.alarm.model.User;

public interface UserDao {
	User selectByPrimaryKey(Integer id);
	int updateByPrimaryKey(User user);
	Long selectCount();
	List<User> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

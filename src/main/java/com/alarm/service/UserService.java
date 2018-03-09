package com.alarm.service;

import java.util.List;

import com.alarm.model.User;

public interface UserService {
	User selectByPrimaryKey(Integer id);
	int updateByPrimaryKey(User user);
	Long selectCount();
	List<User> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

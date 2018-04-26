package com.alarm.dao;

import java.util.List;

import com.alarm.model.User;

public interface UserDao {
	User selectByPrimaryKey(Integer id);
	int insert(User user);
	int updateByPrimaryKey(User user);
	int deleteByPrimaryKey(User user);
	Long selectCount();
	List<User> selectAll(String orderBy, String ascend, int offset, int pageSize);
	User selectByUsername(String username);
	User selectByNumber(Integer number);
	int increUnread(User user);
	int clearUnread(User user);
}

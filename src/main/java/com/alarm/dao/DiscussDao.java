package com.alarm.dao;

import java.util.List;

import com.alarm.model.Discuss;

public interface DiscussDao {
	Discuss selectByPrimaryKey(Integer id);
	int insert(Discuss discuss);
	int updateByPrimaryKey(Discuss discuss);
	int deleteByPrimaryKey(Discuss discuss);
	Long selectCount();
	List<Discuss> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

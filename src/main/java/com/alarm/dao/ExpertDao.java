package com.alarm.dao;

import java.util.List;

import com.alarm.model.Expert;

public interface ExpertDao {
	Expert selectByPrimaryKey(Integer id);
	Expert selectByDiscussId(Integer discussId);
	int insert(Expert expert);
	int updateByPrimaryKey(Expert expert);
	int deleteByPrimaryKey(Expert expert);
	Long selectCount();
	List<Expert> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

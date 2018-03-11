package com.alarm.service;

import java.util.List;

import com.alarm.model.Expert;

public interface ExpertService {
	Expert selectByPrimaryKey(Integer id);
	int insert(Expert expert);
	int updateByPrimaryKey(Expert expert);
	int deleteByPrimaryKey(Expert expert);
	Long selectCount();
	List<Expert> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

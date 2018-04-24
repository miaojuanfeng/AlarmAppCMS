package com.alarm.service;

import java.util.List;

import com.alarm.model.Star;

public interface StarService {
	Star selectByPrimaryKey(Integer id);
	int insert(Star star);
	int updateByPrimaryKey(Star star);
	int deleteByPrimaryKey(Star star);
	Long selectCount();
	List<Star> selectAll(String orderBy, String ascend, int offset, int pageSize);
	List<Star> selectAllByWeek();
	Star selectByUserId(Integer user_id);
}

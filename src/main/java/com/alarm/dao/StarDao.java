package com.alarm.dao;

import java.util.List;

import com.alarm.model.Star;

public interface StarDao {
	Star selectByPrimaryKey(Integer id);
	int insert(Star star);
	int updateByPrimaryKey(Star star);
	int deleteByPrimaryKey(Star star);
	Long selectCount();
	List<Star> selectAll(String orderBy, String ascend, int offset, int pageSize);
	List<Star> selectAll(Integer week);
	Star selectByUserId(Integer user_id);
}

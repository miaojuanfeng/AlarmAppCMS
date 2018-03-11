package com.alarm.dao;

import java.util.List;

import com.alarm.model.Comment;

public interface CommentDao {
	Comment selectByPrimaryKey(Integer id);
	int insert(Comment comment);
	int updateByPrimaryKey(Comment comment);
	int deleteByPrimaryKey(Comment comment);
	Long selectCount();
	List<Comment> selectAll(String orderBy, String ascend, int offset, int pageSize);
}

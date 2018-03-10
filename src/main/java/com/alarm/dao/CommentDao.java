package com.alarm.dao;

import com.alarm.model.Comment;

public interface CommentDao {
	Comment selectByPrimaryKey(Integer id);
	int insert(Comment comment);
}

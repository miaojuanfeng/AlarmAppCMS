package com.alarm.service;

import com.alarm.model.Comment;

public interface CommentService {
	Comment selectByPrimaryKey(Integer id);
	int insert(Comment comment);
}

package com.alarm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.CommentDao;
import com.alarm.model.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentDao commentDao;

	public Comment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return commentDao.selectByPrimaryKey(id);
	}

	public int insert(Comment comment) {
		// TODO Auto-generated method stub
		Date date = new Date();
		comment.setCreateDate(date);
		comment.setModifyDate(date);
		comment.setDeleted(0);
		return commentDao.insert(comment);
	}
	
}

package com.alarm.service;

import java.util.Date;
import java.util.List;

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

	public int updateByPrimaryKey(Comment comment) {
		// TODO Auto-generated method stub
		comment.setModifyDate(new Date());
		return commentDao.updateByPrimaryKey(comment);
	}

	public int deleteByPrimaryKey(Comment comment) {
		// TODO Auto-generated method stub
		comment.setModifyDate(new Date());
		comment.setDeleted(1);
		return commentDao.deleteByPrimaryKey(comment);
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		return commentDao.selectCount();
	}

	public List<Comment> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return commentDao.selectAll(orderBy, ascend, offset, pageSize);
	}

	public List<Comment> selectByDiscussId(Integer DiscussId, String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return commentDao.selectByDiscussId(DiscussId, orderBy, ascend, offset, pageSize);
	}
	
}

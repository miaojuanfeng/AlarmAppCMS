package com.alarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.DiscussDao;
import com.alarm.model.Discuss;

@Service
public class DiscussServiceImpl implements DiscussService {
	
	@Autowired
	private DiscussDao discussDao;

	public Discuss selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return discussDao.selectByPrimaryKey(id);
	}

	public int insert(Discuss discuss) {
		// TODO Auto-generated method stub
		Date date = new Date();
		discuss.setCreateDate(date);
		discuss.setModifyDate(date);
		discuss.setDeleted(0);
		return discussDao.insert(discuss);
	}

	public int updateByPrimaryKey(Discuss discuss) {
		// TODO Auto-generated method stub
		discuss.setModifyDate(new Date());
		return discussDao.updateByPrimaryKey(discuss);
	}

	public int deleteByPrimaryKey(Discuss discuss) {
		// TODO Auto-generated method stub
		discuss.setModifyDate(new Date());
		discuss.setDeleted(1);
		return discussDao.deleteByPrimaryKey(discuss);
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		return discussDao.selectCount();
	}

	public List<Discuss> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return discussDao.selectAll(orderBy, ascend, offset, pageSize);
	}

	public List<Discuss> selectByUser(Integer user_id, String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return discussDao.selectByUser(user_id, orderBy, ascend, offset, pageSize);
	}
}

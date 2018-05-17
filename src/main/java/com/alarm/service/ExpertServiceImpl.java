package com.alarm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.ExpertDao;
import com.alarm.model.Expert;

@Service
public class ExpertServiceImpl implements ExpertService {
	
	@Autowired
	private ExpertDao expertDao;

	public Expert selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return expertDao.selectByPrimaryKey(id);
	}

	public int insert(Expert expert) {
		// TODO Auto-generated method stub
		Date date = new Date();
		expert.setCreateDate(date);
		expert.setModifyDate(date);
		expert.setDeleted(0);
		return expertDao.insert(expert);
	}

	public int updateByPrimaryKey(Expert expert) {
		// TODO Auto-generated method stub
		expert.setModifyDate(new Date());
		return expertDao.updateByPrimaryKey(expert);
	}

	public int deleteByPrimaryKey(Expert expert) {
		// TODO Auto-generated method stub
		expert.setModifyDate(new Date());
		expert.setDeleted(1);
		return expertDao.deleteByPrimaryKey(expert);
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		return expertDao.selectCount();
	}

	public List<Expert> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return expertDao.selectAll(orderBy, ascend, offset, pageSize);
	}

	public Expert selectByDiscussId(Integer discussId) {
		// TODO Auto-generated method stub
		return expertDao.selectByDiscussId(discussId);
	}

}

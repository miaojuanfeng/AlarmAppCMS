package com.alarm.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.StarDao;
import com.alarm.model.Star;

@Service
public class StarServiceImpl implements StarService {
	
	@Autowired
    private StarDao starDao;

	public Star selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return starDao.selectByPrimaryKey(id);
	}

	public Long selectCount() {
		// TODO Auto-generated method stub
		return starDao.selectCount();
	}

	public List<Star> selectAll(String orderBy, String ascend, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return starDao.selectAll(orderBy, ascend, offset, pageSize);
	}

	public int updateByPrimaryKey(Star star) {
		// TODO Auto-generated method stub
		star.setModifyDate(new Date());
		return starDao.updateByPrimaryKey(star);
	}

	public int insert(Star star) {
		// TODO Auto-generated method stub
		Date date = new Date();
		star.setCreateDate(date);
		star.setModifyDate(date);
		star.setDeleted(0);
		return starDao.insert(star);
	}

	public int deleteByPrimaryKey(Star star) {
		// TODO Auto-generated method stub
		star.setModifyDate(new Date());
		star.setDeleted(1);
		return starDao.deleteByPrimaryKey(star);
	}

	public List<Star> selectAllByWeek() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int week = calendar.get(Calendar.WEEK_OF_YEAR);
		return starDao.selectAll(week);
	}

	public Star selectByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return starDao.selectByUserId(user_id);
	}

}

package com.alarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.EaUserDao;

@Service
public class EaUserServiceImpl implements EaUserService {
	
	@Autowired
    private EaUserDao eaUserDao;

	public String selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return eaUserDao.selectByPrimaryKey(id);
	}

}

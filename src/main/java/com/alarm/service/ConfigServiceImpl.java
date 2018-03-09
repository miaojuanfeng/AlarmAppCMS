package com.alarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alarm.dao.ConfigDao;
import com.alarm.model.Config;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigDao configDao;
	
	public Config selectByTitle(String title) {
		// TODO Auto-generated method stub
		return configDao.selectByTitle(title);
	}

	public int updateValueByTitle(Config config) {
		// TODO Auto-generated method stub
		return configDao.updateValueByTitle(config);
	}

}

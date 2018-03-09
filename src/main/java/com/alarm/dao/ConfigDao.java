package com.alarm.dao;

import com.alarm.model.Config;

public interface ConfigDao {
	Config selectByTitle(String title);
	int updateValueByTitle(Config config);
}

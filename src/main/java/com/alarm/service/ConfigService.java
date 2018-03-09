package com.alarm.service;

import com.alarm.model.Config;

public interface ConfigService {
	Config selectByTitle(String title);
	int updateValueByTitle(Config config);
}

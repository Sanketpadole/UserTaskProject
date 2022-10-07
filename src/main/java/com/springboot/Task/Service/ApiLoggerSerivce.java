package com.springboot.Task.Service;

import org.springframework.stereotype.Service;

import com.springboot.Task.Entity.ApiLoggerEntity;

@Service
public interface ApiLoggerSerivce {

	public void createApiLog(ApiLoggerEntity apiDetail);

}

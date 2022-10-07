package com.springboot.Task.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Task.Entity.ApiLoggerEntity;
import com.springboot.Task.Repository.ApiLoggerRepository;
import com.springboot.Task.Service.ApiLoggerSerivce;


@Service
public class ApiLoggerServiceImpl implements ApiLoggerSerivce {

	@Autowired
	private ApiLoggerRepository apiLoggerRepository;

	@Override
	public void createApiLog(ApiLoggerEntity apiDetail) {
		
		apiLoggerRepository.save(apiDetail);

	}

}

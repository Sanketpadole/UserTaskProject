package com.springboot.Task.Service;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.LoggerDto;
import com.springboot.Task.Entity.LoggerEntity;
import com.springboot.Task.Entity.Users;

@Service
public interface LoggerService {

	void createlogger(LoggerDto logger, Users users);

	LoggerEntity getLoggerDetail(String requestheader);

}

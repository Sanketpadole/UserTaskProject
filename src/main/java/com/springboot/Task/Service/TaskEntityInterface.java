package com.springboot.Task.Service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskEntityDto;

import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Entity.StatusEnum;

@Service
public interface TaskEntityInterface {

	void addTask(TaskEntityDto taskEntityDto);

	void updateTask(TaskEntityDto taskEntityDto, Long id);

	void deleteTask(Long id);

	TaskEntityDto getTaskByDto(Long id);

	ResponseEntity<?> updatetaskbyadmin(TaskEntityDto taskEntityDto, Long id, HttpServletRequest request);

//	Page<ITaskEntityDto> getAlltasks(String search, String pageNumber, String pageSize);


	

	

	


	Page<ITaskEntityDto> getAlltasks11(Date startDate, Date endDate, StatusEnum statusEnum, String search,
			String pageNumber, String pageSize);

//	Page<ITaskEntityDto> getAlltasks(Date startDate, Date endDate, String search, String pageNumber, String pageSize);

	

}

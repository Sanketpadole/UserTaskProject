package com.springboot.Task.Service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskEntityDto;

import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Dto.TaskEnumDto;
import com.springboot.Task.Entity.TaskEntity;

@Service
public interface TaskEntityInterface {

	void addTask(TaskEntityDto taskEntityDto);

	void updateTask(TaskEntityDto taskEntityDto, Long id);



	void deleteTask(Long id);

	TaskEntityDto getTaskByDto(Long id);

	

	

	ResponseEntity<?> updatetaskbyuserid(TaskEntityDto taskEntityDto, Long id);






	ResponseEntity<?> updatetaskbyadmin(TaskEntityDto taskEntityDto, Long id, HttpServletRequest request);

	




	Page<ITaskEntityDto> getAlltasks(String search, String pageNumber, String pageSize);



	



	

}

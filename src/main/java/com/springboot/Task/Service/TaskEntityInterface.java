package com.springboot.Task.Service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskEntityDto;

import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Entity.TaskEntity;

@Service
public interface TaskEntityInterface {

	void addTask(TaskEntityDto taskEntityDto);

	void updateTask(TaskEntityDto taskEntityDto, Long id);

	Page<ITaskEntityDto> getAllRoles(String search, String pageNumber, String pageSize);

	void deleteTask(Long id);

	TaskEntityDto getTaskByDto(Long id);

//	TaskEntity updateTask(TaskEntity taskEntity, Long id);

}

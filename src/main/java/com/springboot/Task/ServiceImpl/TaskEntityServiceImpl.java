package com.springboot.Task.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Dto.ITaskListDto;
import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Entity.StatusEnum;
import com.springboot.Task.Entity.TaskEntity;
import com.springboot.Task.Entity.TaskHistory;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.Pagination;
import com.springboot.Task.Repository.HistoryRepository;
import com.springboot.Task.Repository.TaskEntityRepository;
import com.springboot.Task.Repository.UserRoleRepository;
import com.springboot.Task.Repository.UsersRepository;
import com.springboot.Task.Service.TaskEntityInterface;

import Exception.ResourceNotFoundException;

@Service
public class TaskEntityServiceImpl implements TaskEntityInterface {
	@Autowired
	private TaskEntityRepository taskEntityRepository;
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private HistoryRepository historyRepository;

	@Override
	public void addTask(TaskEntityDto taskEntityDto) {

		Users users = this.usersRepository.findById(taskEntityDto.getUserId()).get();
		TaskEntity taskEntity = new TaskEntity();

		taskEntity.setDescription(taskEntityDto.getDescription());
		taskEntity.setTaskName(taskEntityDto.getTaskName());
		taskEntity.setStartDate(taskEntityDto.getStartDate());
		taskEntity.setEndDate(taskEntityDto.getEndDate());
		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
		taskEntity.setUserId(users);

		taskEntityRepository.save(taskEntity);

	}

	@Override
	public void updateTask(TaskEntityDto taskEntityDto, Long id) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity = taskEntityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("task not found with given id"));

		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
		taskEntityRepository.save(taskEntity);

		TaskHistory taskHistory = new TaskHistory();
		taskHistory.setTaskId(taskEntity.getId());
		taskHistory.setDescription(taskEntityDto.getDescription());
		taskHistory.setName(taskEntityDto.getTaskName());
		taskHistory.setStartDate(taskEntityDto.getStartDate());
		taskHistory.setEndDate(taskEntityDto.getEndDate());
		historyRepository.save(taskHistory);

	}

	public Page<ITaskEntityDto> getAllRoles(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {

			return taskEntityRepository.findByOrderById(paging, ITaskEntityDto.class);

		} else {
			return taskEntityRepository.findBytaskName(search, paging, ITaskEntityDto.class);
		}
	}

	@Override
	public TaskEntityDto getTaskByDto(Long id) {
		TaskEntity taskEntity = taskEntityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("task not found with given id"));

		return this.UserToDto(taskEntity);
	}

	@Override
	public void deleteTask(Long id) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity = taskEntityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("task not found with given id"));
		taskEntityRepository.delete(taskEntity);

	}

//	@Override
//	public TaskEntity updateTask(TaskEntity taskEntity, Long id) {
//		TaskEntity taskEntity1 = this.taskEntityRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("not found"));
//
//		taskEntity1.setStatusEnum(taskEntity1.getStatusEnum());
//
//		taskEntityRepository.save(taskEntity1);
//
//		TaskHistory taskHistory = new TaskHistory();
//
//		taskHistory.setTaskId(taskEntity1.getId());
//		taskHistory.setName(taskEntity1.getTaskName());
//		taskHistory.setDescription(taskEntity1.getDescription());
//		taskHistory.setStatus(taskEntity1.getStatusEnum());
//		taskHistory.setStartDate(taskEntity1.getStartDate());
//		taskHistory.setEndDate(taskEntity1.getEndDate());
//
//		historyRepository.save(taskHistory);
//
//		return taskEntity1;
//	}

	TaskEntity dtoToUser(@RequestBody TaskEntityDto taskEntityDto) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setDescription(taskEntityDto.getDescription());
		taskEntity.setTaskName(taskEntityDto.getTaskName());
		taskEntity.setStartDate(taskEntityDto.getStartDate());
		taskEntity.setEndDate(taskEntityDto.getEndDate());
		return taskEntity;
	}

	TaskEntityDto UserToDto(@RequestBody TaskEntity taskEntity) {
		TaskEntityDto taskEntityDto = new TaskEntityDto();
		taskEntityDto.setDescription(taskEntity.getDescription());
		taskEntityDto.setTaskName(taskEntity.getTaskName());
		taskEntityDto.setStartDate(taskEntity.getStartDate());
		taskEntityDto.setEndDate(taskEntity.getEndDate());
		return taskEntityDto;
	}

}

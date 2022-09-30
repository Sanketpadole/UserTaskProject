package com.springboot.Task.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Entity.StatusEnum;
import com.springboot.Task.Entity.TaskEntity;
import com.springboot.Task.Entity.TaskHistory;
import com.springboot.Task.Entity.UserRoleEntity;
import com.springboot.Task.Entity.Users;
import com.springboot.Task.Page.Pagination;
import com.springboot.Task.Repository.HistoryRepository;
import com.springboot.Task.Repository.TaskEntityRepository;
import com.springboot.Task.Repository.UserRoleRepository;
import com.springboot.Task.Repository.UsersRepository;
import com.springboot.Task.Security.JwtTokenUtil;
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

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public void addTask(TaskEntityDto taskEntityDto) {

		Users users = this.usersRepository.findById(taskEntityDto.getUserId()).get();
		TaskEntity taskEntity = new TaskEntity();

		taskEntity.setDescription(taskEntityDto.getDescription());
		taskEntity.setTaskName(taskEntityDto.getTaskName());
		taskEntity.setStartDate(taskEntityDto.getStartDate());
		taskEntity.setEndDate(taskEntityDto.getEndDate());
		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
//		taskEntity.setUserId(users);

		taskEntityRepository.save(taskEntity);

	}
	
	

	
	
	
	
	
	

	@Override
	public void updateTask(TaskEntityDto taskEntityDto, Long id) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity = taskEntityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("task not found with given id"));

		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());

		TaskHistory taskHistory = new TaskHistory();
		taskHistory.setTaskId(taskEntity.getId());

		taskHistory.setName(taskEntityDto.getTaskName());

		historyRepository.save(taskHistory);

	}

	public Page<ITaskEntityDto> getAlltasks11(Date startDate,Date endDate,StatusEnum statusEnum,String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		
		if ( (startDate == null) || (endDate == null) || (statusEnum == null)  ) {

			return taskEntityRepository.findByOrderById(paging, ITaskEntityDto.class);
			
			

		}

		
		
		else {
			
			return taskEntityRepository.findByStartDateOrEndDateOrStatusEnumOrderById(startDate,endDate,statusEnum, paging, ITaskEntityDto.class);
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

//	@Override
//	public ResponseEntity<?> updatetaskbyuserid(@RequestBody TaskEntityDto taskEntityDto, @PathVariable Long id) {
//
//		TaskEntity taskEntity = taskEntityRepository.findById(id)
//				.orElseThrow(() -> (new ResourceNotFoundException("task not found with given id")));
//		taskEntity.setTaskName(taskEntityDto.getTaskName());
//		taskEntity.setDescription(taskEntityDto.getDescription());
//		taskEntity.setStartDate(taskEntityDto.getStartDate());
//		taskEntity.setEndDate(taskEntityDto.getEndDate());
//		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
//		taskEntity.setId(taskEntityDto.getUserId());
//		taskEntityRepository.save(taskEntity);
//
//		return new ResponseEntity<SuccessResponseDto>(
//				new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
//				HttpStatus.ACCEPTED);
//	}

	@Override
	public ResponseEntity<?> updatetaskbyadmin(TaskEntityDto taskEntityDto, Long id, HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();
		System.out.println("fkjg" + user);

		UserRoleEntity userrole = userRoleRepository.finduseridById(user);
		String role = userrole.getPk().getRole().getRoleName();
		System.out.println("efwiu"+role);

		try {
			if (role.equals("Admin")) {
				TaskEntity taskEntity = taskEntityRepository.findById(id)
						.orElseThrow(() -> (new ResourceNotFoundException("not found")));
				taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
				taskEntityRepository.save(taskEntity);
				return new ResponseEntity<SuccessResponseDto>(
						new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
						HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			System.out.println("not found");
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
				HttpStatus.ACCEPTED);

	}










//	@Override
//	public Page<ITaskEntityDto> getAlltasks11(Date startDate, Date endDate, StatusEnum statusEnum, String search,
//			String pageNumber, String pageSize) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}

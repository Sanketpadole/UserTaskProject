package com.springboot.Task.ServiceImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.Task.Dto.ErrorResponseDto;
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

		TaskEntity taskEntity = new TaskEntity();

		taskEntity.setDescription(taskEntityDto.getDescription());
		taskEntity.setTaskName(taskEntityDto.getTaskName());
		taskEntity.setStartDate(taskEntityDto.getStartDate());
		taskEntity.setEndDate(taskEntityDto.getEndDate());
		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());

		taskEntityRepository.save(taskEntity);

	}

	@Override
	public ResponseEntity<?> updateTask(TaskEntityDto taskEntityDto, Long id) {

		TaskEntity taskEntity = new TaskEntity();
		taskEntity = taskEntityRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("task not found with given id"));

		taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());

		TaskHistory taskHistory = new TaskHistory();
		taskHistory.setStatusEnum(taskEntityDto.getStatusEnum());

		taskHistory.setId(id);

		taskHistory.setName(taskEntityDto.getTaskName());

		historyRepository.save(taskHistory);

		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("Success", "success", historyRepository.save(taskHistory)), HttpStatus.ACCEPTED);

	}

//	@Override
//	public ResponseEntity<?> updateTask(TaskEntityDto taskEntityDto,HttpServletRequest request){
//		final String token = request.getHeader("Authorization");
//		String email;
//		String token1 = token.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token1);
//		Long user = usersRepository.findByEmail(email).getId();
//		
//	}

	public Page<ITaskEntityDto> getAlltasks11(Date startDate, Date endDate, StatusEnum statusEnum, String search,
			String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		taskEntityRepository.findByOrderById(paging, ITaskEntityDto.class);

		return taskEntityRepository.findByStartDateOrEndDateOrStatusEnumOrderById(startDate, endDate, statusEnum,
				paging, ITaskEntityDto.class);
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

	@Override
	public ResponseEntity<?> updatetaskbyadmin(TaskEntityDto taskEntityDto, Long id, HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();

		try {
			UserRoleEntity userrole = userRoleRepository.finduseridById(user);
			String role = userrole.getPk().getRole().getRoleName();

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

	@Override
	public ResponseEntity<?> getAlltasksofuserbyadmin(TaskEntityDto taskEntityDto, HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);

		Long user = usersRepository.findByEmail(email).getId();

		try {
			UserRoleEntity userrole = userRoleRepository.finduseridById(user);
			;

			String role = userrole.getPk().getRole().getRoleName();
			if (role.equals("Admin")) {
				TaskEntity taskEntity = taskEntityRepository.findbyuserId(user);
				taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
				taskEntityRepository.save(taskEntity);
				return new ResponseEntity<SuccessResponseDto>(
						new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
						HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);

		}
		return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);
	}

	@Override
	public ResponseEntity<?> gettaskbyuser(HttpServletRequest request) {
		final String Token1 = request.getHeader("Authorization");
		String token = null;
		String email;
		token = Token1.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token);

		Long user = usersRepository.findByEmail(email).getId();

		taskEntityRepository.findById(user);

		return new ResponseEntity<SuccessResponseDto>(
				new SuccessResponseDto("success", "success", taskEntityRepository.findById(user)), HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> deletetasksbyadmin(Long id, HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();

		try {
			UserRoleEntity userrole = userRoleRepository.finduseridById(user);
			String role = userrole.getPk().getRole().getRoleName();
			if (role.equals("Admin")) {
				TaskEntity taskEntity = taskEntityRepository.findById(id)
						.orElseThrow(() -> (new ResourceNotFoundException("not found")));
				taskEntityRepository.deleteById(id);
				return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
						HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			System.out.println("not found");
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
				HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> getAlltasksbyadmin(HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();

		try {
			UserRoleEntity userrole = userRoleRepository.finduseridById(user);

			String role = userrole.getPk().getRole().getRoleName();
			if (role.equals("Admin")) {

				return new ResponseEntity<SuccessResponseDto>(
						new SuccessResponseDto("success", "success", taskEntityRepository.findAll()),
						HttpStatus.ACCEPTED);

			}

			else {
				System.out.println("not found");
			}
		}

		catch (Exception e) {
			System.out.println("not found");

		}
		return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("Role admin not found", "error"),
				HttpStatus.ACCEPTED);

	}

	@Override
	public ResponseEntity<?> gettaskofuserbyadmin(HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();

		try {
			UserRoleEntity userrole = userRoleRepository.finduseridById(user);

			String role = userrole.getPk().getRole().getRoleName();

			if (role.equals("Admin")) {

				return new ResponseEntity<SuccessResponseDto>(
						new SuccessResponseDto("success", "success", taskEntityRepository.findById(user)),
						HttpStatus.ACCEPTED);

			} else {
				return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
						HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);
		}

	}

	@Override
	public ResponseEntity<?> updatestatusbyuser(TaskEntityDto taskEntityDto, HttpServletRequest request) {
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();

		try {
			TaskEntity taskEntity = taskEntityRepository.findbyuserId(user);

			taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
			taskEntityRepository.save(taskEntity);
			return new ResponseEntity<SuccessResponseDto>(
					new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("not found", "not found"),
					HttpStatus.ACCEPTED);
		}

	}

	@Override
	public ResponseEntity<?> getoverduetask1(HttpServletRequest request) {
		Date ts = new Date();
		final String token = request.getHeader("Authorization");
		String email;
		String token1 = token.substring(7);
		email = jwtTokenUtil.getEmailFromToken(token1);
		Long user = usersRepository.findByEmail(email).getId();
		TaskEntity taskEntity11 = taskEntityRepository.findById(user)
				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		if (taskEntity11.getStatusEnum().toString().equals("IN_PROGRESS")) {
			List<TaskEntity> taskEntity = taskEntityRepository.findByEndDateLessThanAndStatusEnum(ts,
					StatusEnum.IN_PROGRESS);
			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", taskEntity),
					HttpStatus.ACCEPTED);
		}

		else {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<?> assigntasktouser(TaskEntityDto taskEntityDto) {
		try {
			Users user = usersRepository.findById(taskEntityDto.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("not found"));

			TaskEntity taskEntity = taskEntityRepository.findById(taskEntityDto.getTaskId())
					.orElseThrow(() -> new ResourceNotFoundException("not found"));
			taskEntity.setUserId(user);
			taskEntityRepository.save(taskEntity);

			return new ResponseEntity<SuccessResponseDto>(
					new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
					HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.BAD_REQUEST);
		}
	}

}

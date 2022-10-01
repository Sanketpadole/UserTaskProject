package com.springboot.Task.Controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Entity.StatusEnum;

import com.springboot.Task.Service.TaskEntityInterface;

@RestController
@RequestMapping("/user1")
public class UserController {

	@Autowired
	private TaskEntityInterface taskEntityInterface;

//	4th point
	@GetMapping("/user")
	public ResponseEntity<?> gettaskbyuser(HttpServletRequest request) {
		return taskEntityInterface.gettaskbyuser(request);
	}

//		final String Token1 = request.getHeader("Authorization");
//		String token = null;
//		String email;
//		token = Token1.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token);
//		System.out.println("fj" + email);
//		Long user = usersRepository.findByEmail(email).getId();
//		System.out.println("aei" + user);
//
//		taskEntityRepository.findById(user);
//
//		return new ResponseEntity<SuccessResponseDto>(
//				new SuccessResponseDto("success", "success", taskEntityRepository.findById(user)), HttpStatus.ACCEPTED);
//
//	}

//	@GetMapping
//	public ResponseEntity<?> getAllTasks(@RequestParam Date startDate, @RequestParam Date endDate, @RequestParam (defaultValue="") String Search,@RequestParam(defaultValue="1") String pageNumber,@RequestParam(defaultValue="25")String pageSize){
//		Page<ITaskEntityDto> allRoles = taskEntityInterface.getAlltasks(startDate,endDate,Search, pageNumber, pageSize);
//		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
//				HttpStatus.BAD_REQUEST);
//	}

//	8th point
	@GetMapping
	public ResponseEntity<?> getAllTasks(@RequestParam(required = false) Date startDate,
			@RequestParam(required = false) Date endDate, @RequestParam(defaultValue = "") StatusEnum statusEnum,
			@RequestParam(defaultValue = "") String Search, @RequestParam(defaultValue = "1") String pageNumber,
			@RequestParam(defaultValue = "20") String pageSize) {

		Page<ITaskEntityDto> allRoles = taskEntityInterface.getAlltasks11(startDate, endDate, statusEnum, Search,
				pageNumber, pageSize);

		if (allRoles.getTotalElements() != 0) {

			return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
				HttpStatus.BAD_REQUEST);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<?> update(@RequestBody TaskEntityDto taskEntityDto, @PathVariable Long id) {
//		return taskEntityInterface.updatetaskbyuserid(taskEntityDto, id);
//
//	}

//	@PreAuthorize("hasRole('deleteUserById')")

//	7th point
	@DeleteMapping("/{id}")

	public ResponseEntity<?> deletetasksbyadmin(@PathVariable Long id, HttpServletRequest request) {
		return taskEntityInterface.deletetasksbyadmin(id, request);
	}

//		final String token = request.getHeader("Authorization");
//		String email;
//		String token1 = token.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token1);
//		Long user = usersRepository.findByEmail(email).getId();
//
//		UserRoleEntity userrole = userRoleRepository.finduseridById(user);
//		String role = userrole.getPk().getRole().getRoleName();
//
//		try {
//			if (role.equals("Admin")) {
//				TaskEntity taskEntity = taskEntityRepository.findById(id)
//						.orElseThrow(() -> (new ResourceNotFoundException("not found")));
//				taskEntityRepository.deleteById(id);
//				return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
//						HttpStatus.ACCEPTED);
//			}
//		} catch (Exception e) {
//			System.out.println("not found");
//		}
//		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
//				HttpStatus.ACCEPTED);
//
//	}

//	4th Point
//	@PreAuthorize("hasRole('getAllUsers')")
	@GetMapping("/admin")
	public ResponseEntity<?> getAlltasksbyadmin(HttpServletRequest request) {
		return taskEntityInterface.getAlltasksbyadmin(request);
	}

//		final String token = request.getHeader("Authorization");
//		String email;
//		String token1 = token.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token1);
//		Long user = usersRepository.findByEmail(email).getId();
//
//		UserRoleEntity userrole = userRoleRepository.finduseridById(user);
//
//		String role = userrole.getPk().getRole().getRoleName();
//
//		try {
//			if (role.equals("Admin")) {
//
//				return new ResponseEntity<SuccessResponseDto>(
//						new SuccessResponseDto("success", "success", taskEntityRepository.findAll()),
//						HttpStatus.ACCEPTED);
//
//			}
//
//			else {
//				System.out.println("not found");
//			}
//		}
//
//		catch (Exception e) {
//			System.out.println("not found");
//
//		}
//		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
//				HttpStatus.ACCEPTED);
//
//	}

//	9th point

	@GetMapping("/admin1")
	public ResponseEntity<?> gettaskofuserbyadmin(HttpServletRequest request) {
		return taskEntityInterface.gettaskofuserbyadmin(request);
	}

//		final String token = request.getHeader("Authorization");
//		String email;
//		String token1 = token.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token1);
//		Long user = usersRepository.findByEmail(email).getId();
//		System.out.println("aaefjkf" + user);
//
//		UserRoleEntity userrole = userRoleRepository.finduseridById(user);
//
//		String role = userrole.getPk().getRole().getRoleName();
//		System.out.println("fghf" + role);
//
//		if (role.equals("Admin")) {
//
//			return new ResponseEntity<SuccessResponseDto>(
//					new SuccessResponseDto("success", "success", taskEntityRepository.findById(user)),
//					HttpStatus.ACCEPTED);
//
//		}
//
//		else {
//			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
//					HttpStatus.ACCEPTED);
//		}
//	}

	@PatchMapping()
	public ResponseEntity<?> updatestatusbyuser(@RequestBody TaskEntityDto taskEntityDto, HttpServletRequest request) {
		return taskEntityInterface.updatestatusbyuser(taskEntityDto, request);
	}
//		final String token = request.getHeader("Authorization");
//		String email;
//		String token1 = token.substring(7);
//		email = jwtTokenUtil.getEmailFromToken(token1);
//		Long user = usersRepository.findByEmail(email).getId();
//		System.out.println("aaefjkf" + user);
//		try {
//			TaskEntity taskEntity = taskEntityRepository.findbyuserId(user);
//			System.out.println("fhi" + taskEntity);
//			taskEntity.setStatusEnum(taskEntityDto.getStatusEnum());
//			taskEntityRepository.save(taskEntity);
//			return new ResponseEntity<SuccessResponseDto>(
//					new SuccessResponseDto("success", "success", taskEntityRepository.save(taskEntity)),
//					HttpStatus.ACCEPTED);
//		} catch (Exception e) {
//			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("not found", "not found"),
//					HttpStatus.ACCEPTED);
//		}
//
//	}

	@PatchMapping("/admin")
	public ResponseEntity<?> getAlltasksofuserbyadmin(@RequestBody TaskEntityDto taskEntityDto,
			HttpServletRequest request) {

		return taskEntityInterface.getAlltasksofuserbyadmin(taskEntityDto, request);
	}

//	3rd point

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatetaskbyadmin(@RequestBody TaskEntityDto taskEntityDto, @PathVariable Long id,
			HttpServletRequest request) {

		return taskEntityInterface.updatetaskbyadmin(taskEntityDto, id, request);
	}

//	@SuppressWarnings("unlikely-arg-type")
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getoverduetask(@PathVariable Long id) {
//		;
//		Date date = new Date();
//
//		Timestamp ts = new Timestamp(date.getTime());
//
//		TaskEntity taskEntity = taskEntityRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("not found"));
//
//		if (date.compareTo(taskEntity.getEndDate()) == -1
//				&& taskEntity.getStatusEnum().toString().equals("IN_PROGRESS")) {
//
//			taskEntityRepository.findById(id);
//
//			return new ResponseEntity<SuccessResponseDto>(
//					new SuccessResponseDto("success", "success", taskEntityRepository.findById(id)),
//					HttpStatus.ACCEPTED);
//
//		} else {
//			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.ACCEPTED);
//		}
//	}
//	10th point
	@GetMapping("/{id}")
	public ResponseEntity<?> getoverduetask1(@PathVariable Long id) {
		return taskEntityInterface.getoverduetask1(id);
	}
}
//		Date ts = new Date();
//		TaskEntity taskEntity11 = taskEntityRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("not found"));
//
//		if (taskEntity11.getStatusEnum().toString().equals("IN_PROGRESS")) {
//			List<TaskEntity> taskEntity = taskEntityRepository.findByEndDateLessThanAndStatusEnum(ts,
//					StatusEnum.IN_PROGRESS);
//			return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", taskEntity),
//					HttpStatus.ACCEPTED);
//		}
//
//		else {
//			return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto("error", "error"), HttpStatus.BAD_REQUEST);
//		}
//
//	}
//}

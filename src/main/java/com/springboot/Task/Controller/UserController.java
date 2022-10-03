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
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/user")
public class UserController {

	@Autowired
	private TaskEntityInterface taskEntityInterface;

//	4th point_______it will check first userid in task table and fetches the data of same userid
	@GetMapping("/user")
	public ResponseEntity<?> gettaskbyuser(HttpServletRequest request) {
		return taskEntityInterface.gettaskbyuser(request);

	}

	@PostMapping("/assigntask")
	ResponseEntity<?> assigntask(@RequestBody TaskEntityDto taskEntityDto) {
		return taskEntityInterface.assigntasktouser(taskEntityDto);

	}

//	8th point filter
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

//	7th point_____if assign user is admin then and only then he can delete the task
	@DeleteMapping("/{id}")

	public ResponseEntity<?> deletetasksbyadmin(@PathVariable Long id, HttpServletRequest request) {
		return taskEntityInterface.deletetasksbyadmin(id, request);
	}

//	4th Point________it will check first whether the role of user is admin or not after getting useris from token and then will fetches all the tasks of all users

	@GetMapping("/admin")
	public ResponseEntity<?> getAlltasksbyadmin(HttpServletRequest request) {
		return taskEntityInterface.getAlltasksbyadmin(request);
	}

//	9th point_____here we are gettin userdetailsfrom userid if user of that id is admin then he can get the task assign to that user

	@GetMapping("/admin1")
	public ResponseEntity<?> gettaskofuserbyadmin(HttpServletRequest request) {
		return taskEntityInterface.gettaskofuserbyadmin(request);
	}

//	3rd point works only if when users assign task________will get user from token and the same user which got from token can change status of task by calling taskrepository
	@PatchMapping()
	public ResponseEntity<?> updatestatusbyuser(@RequestBody TaskEntityDto taskEntityDto, HttpServletRequest request) {
		return taskEntityInterface.updatestatusbyuser(taskEntityDto, request);
	}

	@PatchMapping("/admin")
	public ResponseEntity<?> getAlltasksofuserbyadmin(@RequestBody TaskEntityDto taskEntityDto,
			HttpServletRequest request) {

		return taskEntityInterface.getAlltasksofuserbyadmin(taskEntityDto, request);
	}

//	3rd 5thpoint________status of task will update only if the assign user is admin

	@PatchMapping("/{id}")
	public ResponseEntity<?> updatetaskbyadmin(@RequestBody TaskEntityDto taskEntityDto, @PathVariable Long id,
			HttpServletRequest request) {

		return taskEntityInterface.updatetaskbyadmin(taskEntityDto, id, request);
	}

//	10th point______it will check the enddate of task if this enddate is less than current date and task is still in progress then user can see this task
	@GetMapping("/overdue")
	public ResponseEntity<?> getoverduetask1(HttpServletRequest request) {
		return taskEntityInterface.getoverduetask1(request);
	}
}

package com.springboot.Task.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.ITaskEntityDto;

import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.TaskEntityDto;
import com.springboot.Task.Service.TaskEntityInterface;

@RestController
@RequestMapping("/Task")
public class TaskEntityController {
	@Autowired
	private TaskEntityInterface taskEntityInterface;

	@PostMapping
	ResponseEntity<?> addtask(@RequestBody TaskEntityDto taskEntityDto) {
		taskEntityInterface.addTask(taskEntityDto);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "success", taskEntityDto),
				HttpStatus.ACCEPTED);

	}

	@PutMapping("/{id}")
	ResponseEntity<?> updateTask(@RequestBody TaskEntityDto taskEntityDto, @PathVariable Long id) {
		taskEntityInterface.updateTask(taskEntityDto, id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", taskEntityDto),
				HttpStatus.ACCEPTED);

	}

//	@GetMapping
//	public ResponseEntity<?> getAllTasks(@RequestParam(defaultValue = "") String Search,
//			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {
//		System.out.println("asjh");
//		Page<ITaskEntityDto> allRoles = taskEntityInterface.getAlltasks(Search, pageNumber, pageSize);
//		System.out.println("ajh");
//		if (allRoles.getTotalElements() != 0) {
//			System.out.println("sdfj");
//			return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
//		}
//		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
//				HttpStatus.BAD_REQUEST);
//	}

	@GetMapping("/{id}")
	public TaskEntityDto getTaskByDto(@PathVariable Long id) {
		return this.taskEntityInterface.getTaskByDto(id);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteTask(@PathVariable Long id) {
		taskEntityInterface.deleteTask(id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Success", "success", null),
				HttpStatus.ACCEPTED);
	}

}

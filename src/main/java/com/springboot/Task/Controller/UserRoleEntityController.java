package com.springboot.Task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.UserRoleRequestDto;
import com.springboot.Task.Entity.UserRoleEntity;
import com.springboot.Task.Repository.UserRoleRepository;
import com.springboot.Task.Service.PermissionServiceInterface;
import com.springboot.Task.Service.UserRoleServiceInterface;

@RestController
@RequestMapping("/userrole")
public class UserRoleEntityController {

	@Autowired
	private UserRoleServiceInterface userRoleServiceInterface;

	@Autowired
	private PermissionServiceInterface permissionserviceInterface;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@PostMapping
	public ResponseEntity<?> add(@RequestBody UserRoleRequestDto userRoleRequest) {
		try {
			System.out.println("efh");
			userRoleServiceInterface.add(userRoleRequest);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", userRoleRequest), HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);

		}

	}

	@GetMapping
	public List<UserRoleEntity> getAll() {

		return this.userRoleServiceInterface.getAll();

	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserRoleRequestDto userRoleRequestDto) {
		try {

			this.userRoleServiceInterface.editUserRole(userRoleRequestDto);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", userRoleRequestDto),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody UserRoleRequestDto userRoleRequest) {

		try {

			this.userRoleServiceInterface.deleteUserRoles(userRoleRequest);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			System.out.print("Not Found...");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}

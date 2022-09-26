package com.springboot.Task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.ErrorResponseDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Dto.UsersDto;
import com.springboot.Task.Service.UserServiceInterface;

import Exception.ResourceNotFoundException;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceInterface userServiceInterface;
	
//	@PreAuthorize("hasRole('getUserById')")
	@GetMapping("/{id}")
	private ResponseEntity<?> getByUserById(@PathVariable(name = "id") Long id)
	{
		try
		{
		UsersDto dto=	userServiceInterface.getUserId(id);
			return new ResponseEntity<>(new SuccessResponseDto("Success","success",dto),HttpStatus.OK);
		}catch(ResourceNotFoundException e)
		{
			return ResponseEntity.ok(new ErrorResponseDto("Enter valid Id..", "Invalid Id"));
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UsersDto roleDto, @PathVariable Long id) {

		try {
			UsersDto roleDto1 = this.userServiceInterface.update(roleDto, id);
			return new ResponseEntity<>(new SuccessResponseDto("Success", "Success", roleDto1), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponseDto("Invalid", "Enter valid Id.."), HttpStatus.NOT_FOUND);
		}

	}
	@PreAuthorize("hasRole('deleteUserById')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long id)
	{
		try
		{
			
		
		this.userServiceInterface.delete(id);
		return new ResponseEntity<>(new SuccessResponseDto("Deleted", "Deleted", "Deleted Successfully"),
				HttpStatus.CREATED);
	}catch(Exception e)
	{
		return new ResponseEntity<>(new ErrorResponseDto("Invalid", "Enter Valid Id.."),
				HttpStatus.NOT_FOUND);
	}
		
	}
	
	@PreAuthorize("hasRole('getAllUsers')")
	@GetMapping
	public List<UsersDto> getAllUsers()
	{
		return userServiceInterface.getAll();

	}

}

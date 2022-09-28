package com.springboot.Task.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Task.Dto.ErrorResponseDto;
import com.springboot.Task.Dto.RolePermissionDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Entity.RolePermissionEntity;
import com.springboot.Task.Service.RolePermissionServiceInterface;

@RestController
@RequestMapping("/RolePermission")
public class RolePermissionController {
	@Autowired
	private RolePermissionServiceInterface rolePermissionServiceInterface;

	@PostMapping
	public ResponseEntity<?> userRoleEntities(@RequestBody RolePermissionDto rolePermissionDto) {
		try {

			RolePermissionDto permissionDto = rolePermissionServiceInterface.addRolepermission(rolePermissionDto);

			return new ResponseEntity<>(permissionDto, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping
	public List<RolePermissionEntity> getRolePermission() {
		return rolePermissionServiceInterface.get();

	}

	@PutMapping

	public void updateUserRole(@RequestBody RolePermissionDto rolePermissionDto) {
		rolePermissionServiceInterface.update(rolePermissionDto);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<?> getPermissionByUserId(@PathVariable("id") Long id) {
		try {

			ArrayList<String> user = this.rolePermissionServiceInterface.getPermissionByUserId(id);

			return new ResponseEntity<>(
					new SuccessResponseDto(" Fetc All User Permissions Successfully", "Success", user), HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), "User Role And Permission Not Found"),
					HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping
	public void DeleteRolePermission(@RequestBody RolePermissionDto rolePermissionDto) {
		rolePermissionServiceInterface.delete(rolePermissionDto);
	}

}

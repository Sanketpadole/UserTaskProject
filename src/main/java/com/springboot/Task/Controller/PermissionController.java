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

import com.springboot.Task.Dto.IPermissionListDto;
import com.springboot.Task.Dto.PermissionDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Service.PermissionServiceInterface;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
	@Autowired
	private PermissionServiceInterface permissionserviceInterface;

	@PostMapping
	public ResponseEntity<?> addPermissions(@RequestBody PermissionDto permissionDto) {
		permissionserviceInterface.addPermissions(permissionDto);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", permissionDto),
				HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getAllTech(@RequestParam(defaultValue = "") String search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {

		Page<IPermissionListDto> getpermission = permissionserviceInterface.getAllpermission(search, pageNumber,
				pageSize);

		if (getpermission.getTotalElements() != 0) {

			return new ResponseEntity<>(getpermission.getContent(), HttpStatus.OK);
		}

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("Not found", "not found", getpermission),
				HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/{id}")
	public PermissionDto getPermisionById(@PathVariable Long id) {
		return permissionserviceInterface.getPermissionById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePermissions(@RequestBody PermissionDto permissionDto, @PathVariable Long id) {
		permissionserviceInterface.updatePermissions(permissionDto, id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", permissionDto),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePermissions(@PathVariable Long id) {
		permissionserviceInterface.deletePermissions(id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("success", "success", null),
				HttpStatus.ACCEPTED);
	}

}

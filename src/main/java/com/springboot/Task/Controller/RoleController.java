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

import com.springboot.Task.Dto.ITaskListDto;
import com.springboot.Task.Dto.RoleDto;
import com.springboot.Task.Dto.SuccessResponseDto;
import com.springboot.Task.Repository.RoleRepository;
import com.springboot.Task.Service.RoleServiceInterface;

@RestController
@RequestMapping("/Roles")
public class RoleController {
	@Autowired
	private RoleServiceInterface roleServiceInterface;

	@Autowired
	private RoleRepository roleRepository;

	@PostMapping("/addroles")
	public ResponseEntity<?> addRoles(@RequestBody RoleDto roleDto) {
		roleServiceInterface.addRoles(roleDto);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("RoleAddedSuccessfully", "Role", roleDto),
				HttpStatus.ACCEPTED);
	}

	@PutMapping("/{id}")

	public ResponseEntity<?> updateRoles(@RequestBody RoleDto roleDto, @PathVariable Long id) {

		roleServiceInterface.updateRoles(id, roleDto);

		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("RoleUpdatedSuccessfully", "Role", roleDto),
				HttpStatus.ACCEPTED);
	}

	@GetMapping
	public ResponseEntity<?> getAllRoles(@RequestParam(defaultValue = "") String Search,
			@RequestParam(defaultValue = "1") String pageNumber, @RequestParam(defaultValue = "20") String pageSize) {

		Page<ITaskListDto> allRoles = roleServiceInterface.getAllRoles(Search, pageNumber, pageSize);

		if (allRoles.getTotalElements() != 0) {

			return new ResponseEntity<>(allRoles.getContent(), HttpStatus.OK);
		}
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("not found", "not found", null),
				HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{id}")

	public RoleDto getRoleById(@PathVariable Long id) {
		return this.roleServiceInterface.getRoleById(id);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoles(@PathVariable Long id) {
		roleServiceInterface.deleteRoles(id);
		return new ResponseEntity<SuccessResponseDto>(new SuccessResponseDto("RoleDeletedSuccessfully", "Role", null),
				HttpStatus.ACCEPTED);
	}

}

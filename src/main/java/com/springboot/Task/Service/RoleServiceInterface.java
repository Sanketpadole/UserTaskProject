package com.springboot.Task.Service;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskListDto;
import com.springboot.Task.Dto.RoleDto;

@Service
public interface RoleServiceInterface {

	void addRoles(RoleDto roleDto);

	void deleteRoles(Long id);

	void updateRoles(Long id, RoleDto roleDto);

	RoleDto getRoleById(Long id);

	Page<ITaskListDto> getAllRoles(String search, String pageNumber, String pageSize);

}

package com.springboot.Task.Service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.IPermissionListDto;
import com.springboot.Task.Dto.PermissionDto;

@Service
public interface PermissionServiceInterface {

	void addPermissions(PermissionDto permissionDto);

	void updatePermissions(PermissionDto permissionDto, Long id);

	void deletePermissions(Long id);

	Page<IPermissionListDto> getAllpermission(String search, String pageNumber, String pageSize);

	PermissionDto getPermissionById(Long id);

}

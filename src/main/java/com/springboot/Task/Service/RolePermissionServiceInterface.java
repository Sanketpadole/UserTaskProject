package com.springboot.Task.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.RolePermissionDto;
import com.springboot.Task.Entity.RolePermissionEntity;

@Service
public interface RolePermissionServiceInterface {

	RolePermissionDto addRolepermission(RolePermissionDto rolePermissionDto);

	void update(RolePermissionDto rolePermissionDto);

	List<RolePermissionEntity> get();

	void delete(RolePermissionDto rolePermissionDto);

	void DeleteRolePermission(RolePermissionDto rolePermissionDto);

	ArrayList<String> getPermissionByUserId(Long userId);

}

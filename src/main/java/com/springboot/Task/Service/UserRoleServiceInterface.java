package com.springboot.Task.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UserRoleRequestDto;
import com.springboot.Task.Entity.UserRoleEntity;

@Service
public interface UserRoleServiceInterface {

	void add(UserRoleRequestDto userRoleRequest);

	void editUserRole(UserRoleRequestDto userRoleRequestDto);

	void deleteUserRoles(UserRoleRequestDto userRoleRequest);

	List<UserRoleEntity> getAll();

}

package com.springboot.Task.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.UserRoleRequestDto;

import com.springboot.Task.Entity.RoleEntity;
import com.springboot.Task.Entity.UserRoleEntity;
import com.springboot.Task.Entity.UserRoleId;
import com.springboot.Task.Entity.Users;

import com.springboot.Task.Repository.AuthRepository;

import com.springboot.Task.Repository.RoleRepository;
import com.springboot.Task.Repository.UserRoleRepository;
import com.springboot.Task.Repository.UsersRepository;
import com.springboot.Task.Service.UserRoleServiceInterface;

import Exception.ResourceNotFoundException;

@Service
public class UserRoleServiceImpl implements UserRoleServiceInterface {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private UserRoleRepository userroleRepository;

	@Override
	public void add(UserRoleRequestDto userRoleRequest) {
		try {

			ArrayList<UserRoleEntity> userRoles = new ArrayList<>();

			Users users = authRepository.findById(userRoleRequest.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

			RoleEntity roleEntity = roleRepository.findById(userRoleRequest.getRoleId())
					.orElseThrow(() -> new ResourceNotFoundException("Role not found with givrn id"));

			UserRoleEntity userRoleEntity = new UserRoleEntity();
			UserRoleId user = new UserRoleId(users, roleEntity);

			userRoleEntity.setPk(user);
			userRoles.add(userRoleEntity);

			this.userRoleRepository.saveAll(userRoles);

		} catch (Exception e) {
			throw new ResourceNotFoundException("Not found");

		}

	}

	@Override
	public List<UserRoleEntity> getAll() {
		List<UserRoleEntity> role = this.userRoleRepository.findAll();

		return role;

	}

	@Override
	public void editUserRole(UserRoleRequestDto userRoleRequestDto) {
		System.out.println("wrhg");

		ArrayList<UserRoleEntity> userRoles = new ArrayList<>();
		Users userId = this.authRepository.findById(userRoleRequestDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("not Found"));
		System.out.println("eqj");
		RoleEntity roleEntityId = roleRepository.findById(userRoleRequestDto.getRoleId())
				.orElseThrow(() -> new ResourceNotFoundException("not Found Id"));

		UserRoleEntity userRoleEntity = new UserRoleEntity();
		UserRoleId userRoleId = new UserRoleId(userId, roleEntityId);
		userRoleEntity.setPk(userRoleId);
		userRoles.add(userRoleEntity);

		this.userRoleRepository.updateUserRole(userId.getId(), roleEntityId.getId());

	}

	@Override
	public void deleteUserRoles(UserRoleRequestDto userRoleRequest) {
		System.out.println("ahjw");
		Users user = this.authRepository.findById(userRoleRequest.getUserId()).get();
		System.out.println("sjk"+user);
		RoleEntity entity = this.roleRepository.findById(userRoleRequest.getRoleId()).get();
		System.out.println("fq"+entity);

		UserRoleId userRoleId = new UserRoleId(user, entity);
		UserRoleEntity roleEntity = new UserRoleEntity();
		roleEntity.setPk(userRoleId);
		System.out.println("qweh");
		

		this.userRoleRepository.delete(roleEntity);

	}

}

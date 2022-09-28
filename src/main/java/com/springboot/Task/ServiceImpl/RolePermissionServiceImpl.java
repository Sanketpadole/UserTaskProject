package com.springboot.Task.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.IPermissionIdList;
import com.springboot.Task.Dto.RolePermissionDto;
import com.springboot.Task.Entity.PermissionEntity;
import com.springboot.Task.Entity.RoleEntity;
import com.springboot.Task.Entity.RolePermissionEntity;
import com.springboot.Task.Entity.RolePermissionId;
import com.springboot.Task.Entity.UserRoleEntity;
import com.springboot.Task.Repository.PermissionRepository;
import com.springboot.Task.Repository.RolePermissionRepository;
import com.springboot.Task.Repository.RoleRepository;
import com.springboot.Task.Repository.UserRoleRepository;

import com.springboot.Task.Service.RolePermissionServiceInterface;

import Exception.ResourceNotFoundException;

@Service
public class RolePermissionServiceImpl implements RolePermissionServiceInterface {
	@Autowired
	private RoleRepository roleEntityRepository;

	@Autowired
	private PermissionRepository permissionRepository;
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	@Autowired
	private UserRoleRepository userRoleEntityRepository;

	@Override
	public RolePermissionDto addRolepermission(RolePermissionDto rolePermissionDto) {

		ArrayList<RolePermissionEntity> rolePermission = new ArrayList<>();

		RoleEntity roleEntity1 = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		PermissionEntity permissionEntity1 = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));

		RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
		RolePermissionId rolePermissionId = new RolePermissionId(roleEntity1, permissionEntity1);
		rolePermissionEntity.setPk(rolePermissionId);
		rolePermission.add(rolePermissionEntity);
		rolePermissionRepository.saveAll(rolePermission);
		return rolePermissionDto;

	}

	@Override
	public void update(RolePermissionDto rolePermissionDto) {

		RoleEntity roleEntity = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		PermissionEntity permissionEntity = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));

		if (roleEntity != null && permissionEntity != null) {
			ArrayList<RolePermissionEntity> rolePermission = new ArrayList<>();
			RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
			RolePermissionId rolePermissionId = new RolePermissionId();
			rolePermissionId.setPermission(permissionEntity);
			rolePermissionId.setRole(roleEntity);
			rolePermissionEntity.setPk(rolePermissionId);
			rolePermission.add(rolePermissionEntity);

			rolePermissionRepository.updateRolePermission(roleEntity.getId(), permissionEntity.getId());

		} else {
			throw new ResourceNotFoundException("not found");
		}
	}

	@Override
	public List<RolePermissionEntity> get() {

		return rolePermissionRepository.findAll();
	}

	@Override
	public void delete(RolePermissionDto rolePermissionDto) {
		RoleEntity roleEntity = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		PermissionEntity permissionEntity = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));

		if (roleEntity != null && permissionEntity != null) {
			ArrayList<RolePermissionEntity> rolePermission = new ArrayList<>();
			RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
			RolePermissionId rolePermissionId = new RolePermissionId();
			rolePermissionId.setPermission(permissionEntity);
			rolePermissionId.setRole(roleEntity);
			rolePermissionEntity.setPk(rolePermissionId);
			rolePermission.add(rolePermissionEntity);

			rolePermissionRepository.delete(rolePermissionEntity);

		} else {
			throw new ResourceNotFoundException("not found");
		}
	}

	@Override
	public void DeleteRolePermission(RolePermissionDto rolePermissionDto) {

	}

	@Override
	public ArrayList<String> getPermissionByUserId(Long userId) {

		ArrayList<UserRoleEntity> roleIds = userRoleEntityRepository.getRolesOfUser(userId);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPk().getRole().getId());

		}

		List<IPermissionIdList> rolesPermission = rolePermissionRepository.findPkPermissionByPkRoleIdIn(roles,
				IPermissionIdList.class);
		ArrayList<String> permissions = new ArrayList<>();

		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;

	}
}

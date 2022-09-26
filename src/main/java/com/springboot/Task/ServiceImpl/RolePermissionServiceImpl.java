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
		System.out.println("wfgh");

		ArrayList<RolePermissionEntity> rolePermission = new ArrayList<>();
		System.out.println("qgi");
//		RoleEntity roleEntity1 = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
//				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		RoleEntity roleEntity1 = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
				.orElseThrow(() -> new ResourceNotFoundException("not found"));
		System.out.println("gk");
		System.out.println("role" + roleEntity1);

		PermissionEntity permissionEntity1 = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));
		System.out.println("role" + permissionEntity1);

		RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
		RolePermissionId rolePermissionId = new RolePermissionId(roleEntity1, permissionEntity1);
		rolePermissionEntity.setPk(rolePermissionId);
		rolePermission.add(rolePermissionEntity);
		rolePermissionRepository.saveAll(rolePermission);
		return rolePermissionDto;

	}

	@Override
	public void update(RolePermissionDto rolePermissionDto) {
		System.out.println("qefgh");

		RoleEntity roleEntity = this.roleEntityRepository.findById(rolePermissionDto.getRoleid())
				.orElseThrow(() -> new ResourceNotFoundException("not found"));

		PermissionEntity permissionEntity = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));
		System.out.println("fhj");

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
		System.out.println("vj" + roleEntity);

		PermissionEntity permissionEntity = this.permissionRepository.findById(rolePermissionDto.getPermissionid())
				.orElseThrow(() -> new ResourceNotFoundException("permission not found"));
		System.out.println("afhj" + permissionEntity);

		if (roleEntity != null && permissionEntity != null) {
			ArrayList<RolePermissionEntity> rolePermission = new ArrayList<>();
			RolePermissionEntity rolePermissionEntity = new RolePermissionEntity();
			RolePermissionId rolePermissionId = new RolePermissionId();
			rolePermissionId.setPermission(permissionEntity);
			rolePermissionId.setRole(roleEntity);
			rolePermissionEntity.setPk(rolePermissionId);
			rolePermission.add(rolePermissionEntity);
			System.out.println("af");
			rolePermissionRepository.delete(rolePermissionEntity);

		} else {
			throw new ResourceNotFoundException("not found");
		}
	}

	@Override
	public void DeleteRolePermission(RolePermissionDto rolePermissionDto) {

	}

//	public ArrayList<String> getPermissionByUserId(Long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}




	@Override
	public ArrayList<String> getPermissionByUserId(Long userId){
		System.out.println("aefh");

		ArrayList<UserRoleEntity> roleIds = userRoleEntityRepository.getRolesOfUser(userId);
		ArrayList<Long> roles = new ArrayList<>();

		for (int i = 0; i < roleIds.size(); i++) {

			roles.add(roleIds.get(i).getPk().getRoleEntity().getId());

		}

		List<IPermissionIdList> rolesPermission = rolePermissionRepository.findPkPermissionByPkRoleIdIn(roles, IPermissionIdList.class);
		ArrayList<String> permissions = new ArrayList<>();
		System.out.println("asfjgh");
		for (IPermissionIdList element : rolesPermission) {

			permissions.add(element.getPkPermissionActionName());

		}

		return permissions;

	}
	}

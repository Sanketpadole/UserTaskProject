package com.springboot.Task.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.ITaskListDto;
import com.springboot.Task.Dto.RoleDto;
import com.springboot.Task.Entity.RoleEntity;
import com.springboot.Task.Page.Pagination;

import com.springboot.Task.Repository.RoleRepository;
import com.springboot.Task.Service.RoleServiceInterface;

import Exception.ResourceNotFoundException;

@Service
public class RoleServiceImpl implements RoleServiceInterface {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void addRoles(RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());

		this.roleRepository.save(roleEntity);

	}

	@Override
	public void updateRoles(Long id, RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();

		roleEntity = this.roleRepository.findById(id)
				.orElseThrow(() -> (new ResourceNotFoundException("Role not found with given id")));
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());

		roleRepository.save(roleEntity);

	}

	public Page<ITaskListDto> getAllRoles(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {

			return roleRepository.findByOrderById(paging, ITaskListDto.class);

		} else {
			return roleRepository.findByroleName(search, paging, ITaskListDto.class);
		}
	}

	@Override
	public void deleteRoles(Long id) {
		this.roleRepository.deleteById(id);

	}

	public RoleDto getRoleById(Long id) {
		RoleEntity roleEntity = roleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Role not found with given id"));
		return this.UserTodto(roleEntity);

	}

	public RoleEntity dtoToUser(RoleDto roleDto) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRoleName(roleDto.getRoleName());
		roleEntity.setDescription(roleDto.getDescription());
		return roleEntity;
	}

	public RoleDto UserTodto(RoleEntity roleEntity) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(roleEntity.getRoleName());
		roleDto.setDescription(roleEntity.getDescription());
		return roleDto;

	}
}

package com.springboot.Task.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.Task.Dto.IPermissionListDto;
import com.springboot.Task.Dto.PermissionDto;
import com.springboot.Task.Entity.PermissionEntity;
import com.springboot.Task.Page.Pagination;
import com.springboot.Task.Repository.PermissionRepository;
import com.springboot.Task.Service.PermissionServiceInterface;

import Exception.ResourceNotFoundException;

@Service
public class PermissionServiceImpl implements PermissionServiceInterface {
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void addPermissions(PermissionDto permissionDto) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setBaseUrl(permissionDto.getBaseUrl());
		permissionEntity.setDescription(permissionDto.getDescription());
		permissionEntity.setMethod(permissionDto.getMethod());
		permissionEntity.setPath(permissionDto.getMethod());
		this.permissionRepository.save(permissionEntity);

	}

	@Override
	public void updatePermissions(PermissionDto permissionDto, Long id) {

		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity = permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("permission not found with given id"));
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setBaseUrl(permissionDto.getBaseUrl());
		permissionEntity.setDescription(permissionDto.getDescription());
		permissionEntity.setMethod(permissionDto.getMethod());
		permissionEntity.setPath(permissionDto.getPath());
		this.permissionRepository.save(permissionEntity);

	}

	public Page<IPermissionListDto> getAllpermission(String search, String pageNumber, String pageSize) {

		Pageable paging = new Pagination().getPagination(pageNumber, pageSize);

		if ((search == "") || (search == null) || (search.length() == 0)) {
			return permissionRepository.findByOrderById(paging, IPermissionListDto.class);
		} else {
			return permissionRepository.findByactionName(search, paging, IPermissionListDto.class);
		}

	}

	@Override
	public void deletePermissions(Long id) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity = permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("permission not found with given id"));
		permissionRepository.deleteById(id);
	}

	@Override
	public PermissionDto getPermissionById(Long id) {
		PermissionEntity permissionEntity = permissionRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("permission not found with given id"));

		return this.DtoToUser(permissionEntity);

	}

	PermissionEntity UserToDto(PermissionDto permissionDto) {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setActionName(permissionDto.getActionName());
		permissionEntity.setBaseUrl(permissionDto.getBaseUrl());
		permissionEntity.setDescription(permissionDto.getDescription());
		permissionEntity.setMethod(permissionDto.getMethod());
		permissionEntity.setPath(permissionDto.getPath());
		return permissionEntity;
	}

	PermissionDto DtoToUser(PermissionEntity permissionEntity) {
		PermissionDto permissionDto = new PermissionDto();
		permissionDto.setActionName(permissionEntity.getActionName());
		permissionDto.setBaseUrl(permissionEntity.getBaseUrl());
		permissionDto.setDescription(permissionEntity.getDescription());
		permissionDto.setMethod(permissionEntity.getMethod());
		permissionDto.setPath(permissionEntity.getPath());
		return permissionDto;

	}

}

package com.springboot.Task.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Dto.IPermissionListDto;
import com.springboot.Task.Entity.PermissionEntity;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

	Page<IPermissionListDto> findByOrderById(Pageable paging, Class<IPermissionListDto> class1);

	Page<IPermissionListDto> findByactionName(String search, Pageable paging, Class<IPermissionListDto> class1);

}

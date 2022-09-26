package com.springboot.Task.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Dto.ITaskListDto;
import com.springboot.Task.Entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	Page<ITaskListDto> findByOrderById(Pageable paging, Class<ITaskListDto> class1);

	Page<ITaskListDto> findByroleName(String search, Pageable paging, Class<ITaskListDto> class1);

}

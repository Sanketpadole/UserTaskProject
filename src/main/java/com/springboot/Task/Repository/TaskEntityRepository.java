package com.springboot.Task.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Entity.TaskEntity;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {

	Page<ITaskEntityDto> findByOrderById(Pageable paging, Class<ITaskEntityDto> class1);

	Page<ITaskEntityDto> findBytaskName(String search, Pageable paging, Class<ITaskEntityDto> class1);

}

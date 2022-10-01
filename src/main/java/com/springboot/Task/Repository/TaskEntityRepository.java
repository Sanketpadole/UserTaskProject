package com.springboot.Task.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Entity.StatusEnum;
import com.springboot.Task.Entity.TaskEntity;

public interface TaskEntityRepository extends JpaRepository<TaskEntity, Long> {

	Page<ITaskEntityDto> findByOrderById(Pageable paging, Class<ITaskEntityDto> class1);

	Page<ITaskEntityDto> findBytaskName(String search, Pageable paging, Class<ITaskEntityDto> class1);

	@Query(value = "SELECT *FROM task u WHERE u.user_id=:id", nativeQuery = true)
	TaskEntity findbyuserId(Long id);

	Page<ITaskEntityDto> findBystartDate(Long search, Pageable paging, Class<ITaskEntityDto> class1);

	Page<ITaskEntityDto> findBydescription(String search, Pageable paging, Class<ITaskEntityDto> class1);

	Page<ITaskEntityDto> findByendDate(String search, Pageable paging, Class<ITaskEntityDto> class1);

	Page<ITaskEntityDto> findByStartDateOrEndDateOrStatusEnumOrderById(Date startDate, Date endDate,
			StatusEnum statusEnum, Pageable paging, Class<ITaskEntityDto> class1);

	List<TaskEntity> findByEndDateLessThanAndStatusEnum(Date ts, StatusEnum st);

}

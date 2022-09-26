package com.springboot.Task.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.Task.Dto.IPermissionIdList;
import com.springboot.Task.Entity.RolePermissionEntity;


public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE role_permission u SET permission_id=:permission_id WHERE u.role_id=:role_id", nativeQuery = true)
	void updateRolePermission(@Param("role_id") Long long1, @Param("permission_id") Long long2);

	List<IPermissionIdList> findPkPermissionByPkRoleIdIn(ArrayList<Long> roles, Class<IPermissionIdList> class1);



}

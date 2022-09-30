package com.springboot.Task.Repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.Task.Entity.UserRoleEntity;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

	@Transactional
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query(value = "UPDATE user_role u SET role_entity_id=:id2 WHERE u.user_entity_id=:id", nativeQuery = true)

	void updateUserRole(Long id, Long id2);

	@Query(value = "SELECT *FROM user_role u WHERE u.user_id=:user_id", nativeQuery = true)
	ArrayList<UserRoleEntity> getRolesOfUser(@Param("user_id") Long user_id);

	@Query(value = "SELECT *FROM user_role u WHERE u.user_id=:user", nativeQuery = true)
	UserRoleEntity finduseridById(@Param("user") Long user);

}

package com.springboot.Task.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.springboot.Task.Dto.ITaskEntityDto;
import com.springboot.Task.Entity.Users;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);

	Users findByUsername(String username);

	
}

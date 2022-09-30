package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.springboot.Task.Entity.Users;

@Repository
public interface AuthRepository extends JpaRepository<Users, Long> {

	Users findByEmail(String email);

	Users findByUsername(String username);

}

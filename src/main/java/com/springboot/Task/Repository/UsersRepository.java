package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}

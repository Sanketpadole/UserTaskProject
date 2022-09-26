package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Entity.LoggerEntity;

public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

}

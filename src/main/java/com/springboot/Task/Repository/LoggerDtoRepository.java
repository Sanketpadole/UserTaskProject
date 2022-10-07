package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Task.Entity.LoggerEntity;

@Repository
public interface LoggerDtoRepository extends JpaRepository<LoggerEntity, Integer> {

	static void removeByToken(String userToken) {

	}

	LoggerEntity findByToken(String token);

}

package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Task.Entity.ApiLoggerEntity;

@Repository
public interface ApiLoggerRepository extends JpaRepository<ApiLoggerEntity,Integer>{

}

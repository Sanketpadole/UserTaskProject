package com.springboot.Task.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Task.Entity.TaskHistory;

public interface HistoryRepository extends JpaRepository<TaskHistory,Long> {

}

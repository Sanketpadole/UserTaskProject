package com.springboot.Task.Dto;





import java.sql.Date;

import com.springboot.Task.Entity.StatusEnum;

public class TaskEntityDto {
	private String taskName;
	private String description;
	
	private Long startDate;
	private Long endDate;
	private Long userId;
	private StatusEnum statusEnum;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public StatusEnum getStatusEnum() {
		return statusEnum;
	}
	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}
	public TaskEntityDto(String taskName, String description, Long startDate, Long endDate, Long userId,
			StatusEnum statusEnum) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.statusEnum = statusEnum;
	}
	public TaskEntityDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}

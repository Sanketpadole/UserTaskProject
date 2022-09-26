package com.springboot.Task.Dto;

import com.springboot.Task.Entity.StatusEnum;

public class TaskEntityDto {
	private String taskName;
	private String description;
	private Long startDate;
	private Long endDate;
	private Long userId;
	private StatusEnum statusEnum;

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public TaskEntityDto(String taskName, String description, Long startDate, Long endDate) {
		super();
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public TaskEntityDto() {
		super();

	}

}

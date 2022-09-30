package com.springboot.Task.Dto;











import java.sql.Date;

import com.springboot.Task.Entity.StatusEnum;

public class TaskEntityDto {
	private Long taskId;
	public TaskEntityDto(Long taskId, String taskName, String description, Date startDate, Date endDate, Long userId,
			StatusEnum statusEnum) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.statusEnum = statusEnum;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	private String taskName;
	private String description;
	
	private Date startDate;
	private Date endDate;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
	public TaskEntityDto(String taskName, String description, Date startDate, Date endDate, Long userId,
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

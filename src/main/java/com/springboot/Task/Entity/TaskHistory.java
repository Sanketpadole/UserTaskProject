package com.springboot.Task.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long taskId;
	private String name;
	private String description;
	private StatusEnum status;
	private long startDate;
	private long endDate;

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskHistory(long taskId, String name, String description, StatusEnum status, long startDate, long endDate) {
		super();
		this.taskId = taskId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum statusEnum) {
		this.status = statusEnum;
	}

	public TaskHistory(long taskId, String name, String description, String status, long startDate, long endDate) {
		super();
		this.taskId = taskId;
		this.name = name;
		this.description = description;

		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public TaskHistory(long taskId, String name, String description, String status, String startDate, String endDate) {
		super();
		this.taskId = taskId;
		this.name = name;
		this.description = description;

	}

	public TaskHistory() {
		super();

	}

}

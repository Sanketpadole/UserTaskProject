package com.springboot.Task.Dto;

import java.util.Date;

import com.springboot.Task.Entity.StatusEnum;

public interface ITaskEntityDto {
	public String getTaskName();

	public String getDescription();

	public Date getStartDate();

	public Date getEndDate();

	public StatusEnum getStatusEnum();

}

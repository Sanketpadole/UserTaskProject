package com.springboot.Task.Dto;

import com.springboot.Task.Entity.StatusEnum;

public class TaskEnumDto {
	private StatusEnum statusEnum;

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public TaskEnumDto(StatusEnum statusEnum) {
		super();
		this.statusEnum = statusEnum;
	}


}

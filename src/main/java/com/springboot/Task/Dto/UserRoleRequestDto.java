package com.springboot.Task.Dto;

public class UserRoleRequestDto {

	private Long userId;
	private Long roleId;

	public UserRoleRequestDto() {
		super();

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public UserRoleRequestDto(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}

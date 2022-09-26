package com.springboot.Task.Entity;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class UserRoleId implements Serializable {
	@ManyToOne
	private Users user;

	public UserRoleId() {
		super();

	}

	public UserRoleId(Users user, RoleEntity roleEntity) {
		super();
		this.user = user;
		this.roleEntity = roleEntity;
	}

	public Users getUserEntity() {
		return user;
	}

	public void setUserEntity(Users userEntity) {
		this.user = user;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

	@ManyToOne
	private RoleEntity roleEntity;

}

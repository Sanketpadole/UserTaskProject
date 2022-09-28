package com.springboot.Task.Entity;

import java.io.Serializable;

import javax.persistence.ManyToOne;

public class UserRoleId implements Serializable {
	@ManyToOne
	private Users user;

	@ManyToOne
	private RoleEntity role;

	public UserRoleId() {
		super();

	}

	public UserRoleId(Users user, RoleEntity role) {
		super();
		this.user = user;
		this.role = role;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}

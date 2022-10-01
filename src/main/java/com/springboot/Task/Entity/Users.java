package com.springboot.Task.Entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity

@Where(clause = "is_Active=true")
@SQLDelete(sql = "UPDATE USERS SET is_active=false WHERE id=?")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private Long id;
	private String username;
	private String password;
	private String email;
	private boolean isActive = true;

	public Users(Long id, String username, String password, String email, boolean isActive) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isActive = isActive;

	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Users() {
		super();

	}

	public Users(Long id, String username, String password, String email, List<UserRoleEntity> userRole) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

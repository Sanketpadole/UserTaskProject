package com.springboot.Task.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class LoggerEntity {
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@CreationTimestamp
	private Date createdAt;
	private Date expireAt;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users userId;

	public LoggerEntity() {
		super();

	}

	public LoggerEntity(Long id, String token, Date createdAt, Date expireAt, Users userId) {
		super();
		this.id = id;
		this.token = token;
		this.createdAt = createdAt;
		this.expireAt = expireAt;
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

}

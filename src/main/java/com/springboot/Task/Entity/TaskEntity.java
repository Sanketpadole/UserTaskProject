package com.springboot.Task.Entity;









import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "task")
@Where(clause = "is_Active=true")
@SQLDelete(sql = "UPDATE TASK SET is_active=false WHERE id=?")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskName;

	private String description;
	private Date startDate;
	private Date endDate;
	private boolean isActive = true;

	@Enumerated(EnumType.STRING)
	private StatusEnum statusEnum;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private Users userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public StatusEnum getStatusEnum() {
		return statusEnum;
	}

	public void setStatusEnum(StatusEnum statusEnum) {
		this.statusEnum = statusEnum;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public TaskEntity(Long id, String taskName, String description, Date startDate, Date endDate, boolean isActive,
			StatusEnum statusEnum, Users userId) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
		this.statusEnum = statusEnum;
		this.userId = userId;
	}

	public TaskEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUserId(Long userId2) {
		// TODO Auto-generated method stub
		
	}

	



//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getTaskName() {
//		return taskName;
//	}
//
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
//
//	public StatusEnum getStatusEnum() {
//		return statusEnum;
//	}
//
//	public void setStatusEnum(StatusEnum statusEnum) {
//		this.statusEnum = statusEnum;
//	}
//
//	public Users getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Users userId) {
//		this.userId = userId;
//	}
//
//	public boolean isActive() {
//		return isActive;
//	}
//
//	public void setActive(boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	public TaskEntity(Long id, String taskName, String description, Date startDate, Date endDate, boolean isActive) {
//		super();
//		this.id = id;
//		this.taskName = taskName;
//		this.description = description;
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.isActive = isActive;
//	}
//
//	public TaskEntity() {
//		super();
//
//	}

}

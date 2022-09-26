package com.springboot.Task.Entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user_role")
@Where(clause = "is_active=true")

@SQLDelete(sql = "UPDATE user_role u SET is_active=false WHERE u.role_entity_id=? AND u.user_entity_id=?")
@AssociationOverrides({ @AssociationOverride(name = "pk.users", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "pk.roles", joinColumns = @JoinColumn(name = "role_id")) })
public class UserRoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRoleId pk = new UserRoleId();

	@CreationTimestamp
	private Date created_At;

	public UserRoleEntity() {
		super();

	}

	public UserRoleEntity(UserRoleId pk, Date created_At, Date updated_At, boolean isActive) {
		super();
		this.pk = pk;
		this.created_At = created_At;
		Updated_At = updated_At;
		this.isActive = isActive;
	}

	public UserRoleId getPk() {
		return pk;
	}

	public void setPk(UserRoleId pk) {
		this.pk = pk;
	}

	public Date getCreated_At() {
		return created_At;
	}

	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}

	public Date getUpdated_At() {
		return Updated_At;
	}

	public void setUpdated_At(Date updated_At) {
		Updated_At = updated_At;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@UpdateTimestamp
	private Date Updated_At;

	@Column(name = "is_active")
	private boolean isActive = true;
}

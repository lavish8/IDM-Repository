package com.identity.manager.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.identity.platform.persistence.domain.JpaAuditable;

@Entity
@Table(name = "user_x_role", catalog = "orgsec_db", uniqueConstraints = @UniqueConstraint(columnNames = { "ROLE__ID",
		"USER__ID" }))
public class UserRole extends JpaAuditable<User, Long> {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE__ID", nullable = false)
	private Role role;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="USER__ID", nullable=false)
	private User user;
	
	@Column(name = "PRIORITY")
	private Long priority;

	public UserRole() {
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

}

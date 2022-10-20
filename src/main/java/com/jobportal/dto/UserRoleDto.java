package com.jobportal.dto;

public class UserRoleDto {

	private Long userId;

	private Long roleId;

	public UserRoleDto(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRoleDto() {
		// TODO Auto-generated constructor stub
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

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}

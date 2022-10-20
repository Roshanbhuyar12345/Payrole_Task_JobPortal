package com.jobportal.dto;

public class PermissionDto {

	private String actionName;

	private String baseUrl;

	private String description;

	private String method;

	public PermissionDto(String actionName, String baseUrl, String description, String method) {
		super();
		this.actionName = actionName;
		this.baseUrl = baseUrl;
		this.description = description;
		this.method = method;
	}

	public PermissionDto() {
		// TODO Auto-generated constructor stub
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}

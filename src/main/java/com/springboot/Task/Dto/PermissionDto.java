package com.springboot.Task.Dto;

public class PermissionDto {

	private String actionName;
	private String description;
	private String method;
	private String baseUrl;
	private String path;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public PermissionDto(String actionName, String description, String method, String baseUrl, String path) {
		super();
		this.actionName = actionName;
		this.description = description;
		this.method = method;
		this.baseUrl = baseUrl;
		this.path = path;
	}

	public PermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}

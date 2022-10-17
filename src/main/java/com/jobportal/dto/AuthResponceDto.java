package com.jobportal.dto;

public class AuthResponceDto {

	private Long id;

	private String userName;

	private String email;

	private String token;

	public AuthResponceDto() {
		// TODO Auto-generated constructor stub
	}

	public AuthResponceDto(Long id, String userName, String email, String token) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

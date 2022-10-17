package com.jobportal.dto;

public class ErrorResponceDto {

	private String error;

	private String errorkey;

	public ErrorResponceDto() {
		// TODO Auto-generated constructor stub
	}

	public ErrorResponceDto(String error, String errorkey) {
		super();
		this.error = error;
		this.errorkey = errorkey;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorkey() {
		return errorkey;
	}

	public void setErrorkey(String errorkey) {
		this.errorkey = errorkey;
	}

}

package com.jobportal.dto;

public class SuccessResponceDto {

	private String massage;

	private String massageKey;

	private Object data;

	public SuccessResponceDto() {
		// TODO Auto-generated constructor stub
	}

	public SuccessResponceDto(String massage, String massageKey, Object data) {
		super();
		this.massage = massage;
		this.massageKey = massageKey;
		this.data = data;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	public String getMassageKey() {
		return massageKey;
	}

	public void setMassageKey(String massageKey) {
		this.massageKey = massageKey;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

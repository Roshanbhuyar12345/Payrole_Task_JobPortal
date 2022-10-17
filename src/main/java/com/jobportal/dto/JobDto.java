package com.jobportal.dto;

public class JobDto {

	private String jobTitle;

	private String description;

	public JobDto(String jobTitle, String description) {
		super();
		this.jobTitle = jobTitle;
		this.description = description;
	}

	public JobDto() {
		// TODO Auto-generated constructor stub
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

package com.jobportal.dto;

public class UserJobDto {

	private Long jobId;

	public UserJobDto(Long jobId) {
		super();
		this.jobId = jobId;
	}

	public UserJobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

}

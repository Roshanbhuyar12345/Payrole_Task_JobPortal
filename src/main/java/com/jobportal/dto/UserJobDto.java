package com.jobportal.dto;

import java.util.List;

public class UserJobDto {

	private Long userId;

	private List<Long> jobId;

	public UserJobDto(Long userId, List<Long> jobId) {
		super();
		this.userId = userId;
		this.jobId = jobId;
	}

	public UserJobDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public List<Long> getJobId() {
		return jobId;
	}

	public void setJobId(List<Long> jobId) {
		this.jobId = jobId;
	}

}

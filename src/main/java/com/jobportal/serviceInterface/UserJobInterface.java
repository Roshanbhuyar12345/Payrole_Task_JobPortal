package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;

import com.jobportal.dto.IListUserJobDto;
import com.jobportal.dto.UserJobDto;

public interface UserJobInterface {

	UserJobDto applyJobs(UserJobDto userJobDto, Long userId) throws Exception;

	Page<IListUserJobDto> getJobsByUserId(Long userId, String pageNumber, String pageSize);

	Page<IListUserJobDto> getUsersByJobId(Long jobId, String pageNumber, String pageSize);
}

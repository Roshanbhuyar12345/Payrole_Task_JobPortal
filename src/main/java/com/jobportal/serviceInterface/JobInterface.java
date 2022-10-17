package com.jobportal.serviceInterface;

import org.springframework.data.domain.Page;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.JobDto;

public interface JobInterface {

	JobDto addJobs(JobDto jobDto, Long userId);

	Page<IListJobDto> getAllJobs(String search, String pagNumber, String pageSize);

}

package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListJobDto;
import com.jobportal.dto.JobDto;
import com.jobportal.entity.JobEntity;
import com.jobportal.repository.JobReposiotry;
import com.jobportal.serviceInterface.JobInterface;
import com.jobportal.utils.Pagination;

@Service
public class JobServiceImpl implements JobInterface {

	@Autowired
	private JobReposiotry jobReposiotry;

	@Override
	public JobDto addJobs(JobDto jobDto, Long UserId) {
		JobEntity entity = new JobEntity();
		entity.setJobTitle(jobDto.getJobTitle());
		entity.setDescription(jobDto.getDescription());
		entity.setCreatedBy(UserId);
		jobReposiotry.save(entity);
		return jobDto;
	}

	@Override
	public Page<IListJobDto> getAllJobs(String search, String pagNumber, String pageSize) {
		Page<IListJobDto> iJobListDto;

		Pageable pageable = new Pagination().getPagination(pagNumber, pageSize);
		if ((search == "") || (search == null) || (search.length() == 0)) {
			iJobListDto = this.jobReposiotry.findByOrderByIdDesc(pageable, IListJobDto.class);
		}
		iJobListDto = this.jobReposiotry.findByJobTitleContainingIgnoreCase(search, pageable, IListJobDto.class);
		return iJobListDto;
	}

}

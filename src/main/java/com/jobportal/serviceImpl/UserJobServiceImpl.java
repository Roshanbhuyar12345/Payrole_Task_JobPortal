package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListUserJobDto;
import com.jobportal.dto.UserJobDto;
import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.entity.UserJobEntity;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobReposiotry;
import com.jobportal.repository.UserJobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.serviceInterface.UserJobInterface;
import com.jobportal.utils.Pagination;

@Service
public class UserJobServiceImpl implements UserJobInterface {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	JobReposiotry jobReposiotry;

	@Autowired
	UserJobRepository userJobRepository;

	@Override
	public UserJobDto applyJobs(UserJobDto userJobDto) throws Exception {

		ArrayList<UserJobEntity> jobs = new ArrayList<>();
		UserEntity userEntity = this.userRepository.findById(userJobDto.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));

		for (int i = 0; i < userJobDto.getJobId().size(); i++) {

			final long jobId = userJobDto.getJobId().get(i);

			JobEntity job = this.jobReposiotry.findById(jobId)
					.orElseThrow(() -> new ResourceNotFoundException("Job not found"));

			UserJobEntity userJob = this.userJobRepository.findByUserIdAndJobId(userJobDto.getUserId(),
					userJobDto.getJobId().get(i));

			if (userJob != null) {

				throw new ResourceNotFoundException("Already applied ");
			}

			UserJobEntity userJobs = new UserJobEntity();
			userJobs.setUser(userEntity);
			userJobs.setJob(job);
			jobs.add(userJobs);

		}
		this.userJobRepository.saveAll(jobs);
		return userJobDto;
	}

	@Override
	public Page<IListUserJobDto> getJobsByUserId(Long userId, String pageNumber, String pageSize) {

		Page<IListUserJobDto> page;

		Pageable pageable = new Pagination().getPagination(pageNumber, pageSize);
		if (((userId == null))) {
			page = this.userJobRepository.findByOrderByIdDesc(pageable, IListUserJobDto.class);
		} else {
			page = userJobRepository.findJobIdByUserIdOrderByIdDesc(userId, pageable, IListUserJobDto.class);
		}

		return page;
	}

	@Override
	public Page<IListUserJobDto> getUsersByJobId(Long jobId, String pageNumber, String pageSize) {

		Page<IListUserJobDto> page;

		Pageable pageable = new Pagination().getPagination(pageNumber, pageSize);
		if (((jobId == null))) {
			page = this.userJobRepository.findByOrderByIdDesc(pageable, IListUserJobDto.class);
		} else {
			page = userJobRepository.findUserIdByJobIdOrderByIdDesc(jobId, pageable, IListUserJobDto.class);
		}

		return page;
	}

}

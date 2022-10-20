package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.Email;
import com.jobportal.dto.IListUserJobDto;
import com.jobportal.dto.UserJobDto;
import com.jobportal.entity.JobEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.entity.UserJobEntity;
import com.jobportal.exception.ResourceNotFoundException;
import com.jobportal.repository.JobReposiotry;
import com.jobportal.repository.UserJobRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.serviceInterface.EmailInterface;
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

	@Autowired
	private EmailInterface emailInterface;

	@Override
	public UserJobDto applyJobs(UserJobDto userJobDto, Long userId) throws Exception {

		UserEntity userEntity = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found."));

		final long jobId = userJobDto.getJobId();
		JobEntity job = this.jobReposiotry.findById(jobId)
				.orElseThrow(() -> new ResourceNotFoundException("Job not found"));

		UserJobEntity userJob = this.userJobRepository.findByUserIdAndJobId(userId, userJobDto.getJobId());

		if (userJob != null) {

			throw new ResourceNotFoundException("Already applied ");
		}

		UserJobEntity userJobs = new UserJobEntity();
		userJobs.setUser(userEntity);
		userJobs.setJob(job);
		UserEntity userEntity2 = this.userRepository.findById(userId).orElseThrow();
		String email = userEntity2.getEmail();

		Email userEntity1 = this.userJobRepository.findAllUserEmail().get(0);

		emailInterface.sendSimpleMessage(email, "Apna jobs", "Job applied sucessfully for  " + job.getJobTitle());

		emailInterface.sendSimpleMessage(userEntity1.getEmail(), "Apna jobs", "Candidate Applied for job"
				+ "Job title    " + job.getJobTitle() + "Candidate Email " + userEntity2.getEmail());

		this.userJobRepository.save(userJobs);

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

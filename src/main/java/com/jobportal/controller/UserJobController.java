package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.IListUserJobDto;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.dto.UserJobDto;
import com.jobportal.serviceInterface.UserJobInterface;
import com.jobportal.utils.ApisUrls;
import com.jobportal.utils.GlobalFuction;

@RestController
@RequestMapping(ApisUrls.USER_JOBS)
public class UserJobController {
	@Autowired

	private UserJobInterface userJobInterface;

	@PostMapping
	public ResponseEntity<?> applyJobs(@RequestBody UserJobDto userJobDto,
			@RequestAttribute(GlobalFuction.CUSTUM_ATTRIBUTE_USER_ID) Long userId) throws Exception {
		try {
			userJobInterface.applyJobs(userJobDto, userId);
			return new ResponseEntity<>(new SuccessResponceDto("Job applied sucessfully", "Sucess", userJobDto),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not applied"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/userId")
	public ResponseEntity<?> getJobsByUserId(@RequestAttribute(GlobalFuction.CUSTUM_ATTRIBUTE_USER_ID) Long userId,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String pageSize) {
		Page<IListUserJobDto> page = this.userJobInterface.getJobsByUserId(userId, pageNo, pageSize);

		return new ResponseEntity<>(new SuccessResponceDto("Jobs apllied by user", "Successfull", page.getContent()),
				HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<?> getUsersByJobId(@RequestParam(defaultValue = "") Long JobId,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String pageSize) {
		Page<IListUserJobDto> page = this.userJobInterface.getUsersByJobId(JobId, pageNo, pageSize);

		return new ResponseEntity<>(
				new SuccessResponceDto("Users who applied to this job", "Successfull", page.getContent()),
				HttpStatus.OK);
	}
}

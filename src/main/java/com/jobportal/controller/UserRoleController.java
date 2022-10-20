package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.IListUserRole;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.dto.UserRoleDto;
import com.jobportal.serviceInterface.UserRoleInterface;
import com.jobportal.utils.ApisUrls;

@RestController
@RequestMapping(ApisUrls.USER_ROLE)
public class UserRoleController {

	@Autowired
	public UserRoleInterface userRoleInterface;

	@PostMapping
	public ResponseEntity<?> addRoleToUser(@RequestBody UserRoleDto userRoleDto) {
		try {
			UserRoleDto roleDto = this.userRoleInterface.addRoleToUser(userRoleDto);
			return new ResponseEntity<>(new SuccessResponceDto("Role assign to user", "Successfull", roleDto),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not assign"), HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping
	public ResponseEntity<?> getAllUserRoles() {
		List<IListUserRole> iListUserRoles = this.userRoleInterface.getAllUserRoles();
		return new ResponseEntity<>(new SuccessResponceDto("Users and there role", "Successfull", iListUserRoles),
				HttpStatus.OK);
	}

}

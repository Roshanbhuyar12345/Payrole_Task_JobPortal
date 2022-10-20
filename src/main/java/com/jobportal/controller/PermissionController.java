package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.PermissionDto;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.serviceInterface.PermissionInterface;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionInterface permissionInterface;

	@PreAuthorize("hasRole('addPermission')")
	@PostMapping
	public ResponseEntity<?> addPermission(@RequestBody PermissionDto permissionDto) {
		try {
			this.permissionInterface.addPermission(permissionDto);
			return new ResponseEntity<>(new SuccessResponceDto("Permission added", "Successfull", permissionDto),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not added"), HttpStatus.OK);
		}

	}

}

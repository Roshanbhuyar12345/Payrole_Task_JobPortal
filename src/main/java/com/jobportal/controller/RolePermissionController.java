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
import com.jobportal.dto.RolePermissionDto;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.serviceInterface.RolePermissionInterface;

@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {

	@Autowired
	private RolePermissionInterface rolePermissionInterface;

	@PreAuthorize("hasRole('addRoleToPermission')")
	@PostMapping()
	public ResponseEntity<?> addRoleToPermission(@RequestBody RolePermissionDto rolePermissionDto) {
		try {

			RolePermissionDto dto = this.rolePermissionInterface.addPermissionToRole(rolePermissionDto);

			return new ResponseEntity<>(new SuccessResponceDto("Assign Succefully", "Assigned", dto), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not assign"), HttpStatus.BAD_REQUEST);
		}

	}

}

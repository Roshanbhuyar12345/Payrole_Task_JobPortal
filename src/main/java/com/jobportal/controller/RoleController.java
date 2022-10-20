package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.IListRoleDto;
import com.jobportal.dto.RoleDto;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.serviceInterface.RoleInterface;
import com.jobportal.utils.ApisUrls;

@RestController
@RequestMapping(ApisUrls.ROLE)
public class RoleController {

	@Autowired
	private RoleInterface roleInterface;

	@PostMapping
	public ResponseEntity<?> addRoleToUser(@RequestBody RoleDto roleDto) {
		try {
			this.roleInterface.addRoleToUser(roleDto);
			return new ResponseEntity<>(new SuccessResponceDto("Role added", "Successfull", roleDto), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not added"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateRole(@PathVariable("id") Long roleId, @RequestBody RoleDto roleDto)
			throws Exception {
		try {
			this.roleInterface.updateRole(roleId, roleDto);
			return new ResponseEntity<>(new SuccessResponceDto("Updated role", "Successfull", roleDto), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not updated"), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id") Long roleId, RoleDto roleDto) throws Exception {
		try {
			this.roleInterface.deleteRole(roleId, roleDto);
			return new ResponseEntity<>(new SuccessResponceDto("Role deleted", "Successfull", "Deleted"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not deleted"), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<?> getAllRoles() {
		List<IListRoleDto> list = this.roleInterface.getAllRoles();
		return new ResponseEntity<>(new SuccessResponceDto("Roles", "Successfull", list), HttpStatus.OK);
	}
}

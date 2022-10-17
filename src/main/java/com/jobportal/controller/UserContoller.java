package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.dto.UserDto;
import com.jobportal.serviceInterface.UserInterface;
import com.jobportal.utils.ApisUrls;

@RestController
@RequestMapping(ApisUrls.USER)
public class UserContoller {

	@Autowired
	private UserInterface userInterface;

	@PutMapping("{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto)
			throws Exception {
		try {
			this.userInterface.updateUser(userId, userDto);
			return new ResponseEntity<>(new SuccessResponceDto("Updated", "Successfull", "User updated successfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not updated"), HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Long userId) {

		List<IListUserDto> iListUserDtos = this.userInterface.getUserById(userId);
		return new ResponseEntity<>(new SuccessResponceDto("User", "Successfull", iListUserDtos), HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<?> getAllUser(@RequestParam(defaultValue = "") String userName,
			@RequestParam(defaultValue = "1") String pageNo, @RequestParam(defaultValue = "25") String pageSize) {
		Page<IListUserDto> users = this.userInterface.getAllUsers(userName, pageNo, pageSize);

		return new ResponseEntity<>(new SuccessResponceDto("Success", "Users", users.getContent()), HttpStatus.OK);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable("id") Long userId) throws Exception {
		try {
			this.userInterface.deleteUserById(userId);

			return new ResponseEntity<>(new SuccessResponceDto("Deleted", "Succesfull", "Deleted Successfully"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not deleted"), HttpStatus.BAD_REQUEST);

		}
	}

}

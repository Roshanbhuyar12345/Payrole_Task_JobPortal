package com.jobportal.serviceInterface;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.UserDto;

public interface UserInterface {

	UserDto updateUser(Long userId, UserDto userDto) throws Exception;

	List<IListUserDto> getUserById(Long userId);

	Page<IListUserDto> getAllUsers(String userName, String pageNumber, String pageSize);

	public void deleteUserById(Long userId) throws Exception;
}

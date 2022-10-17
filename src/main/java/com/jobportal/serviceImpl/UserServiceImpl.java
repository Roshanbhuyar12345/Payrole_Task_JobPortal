package com.jobportal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.dto.IListUserDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.UserEntity;
import com.jobportal.repository.UserRepository;
import com.jobportal.serviceInterface.UserInterface;
import com.jobportal.utils.Pagination;

@Service
public class UserServiceImpl implements UserInterface {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) throws Exception {

		UserEntity userEntity = this.userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
		userEntity.setEmail(userDto.getEmail());
		userEntity.setEducation(userDto.getEducation());
		userEntity.setExperience(userDto.getExperience());
		userEntity.setUserName(userDto.getUserName());
		this.userRepository.save(userEntity);
		return userDto;
	}

	@Override
	public List<IListUserDto> getUserById(Long userId) {
		List<IListUserDto> iListUserDtos = this.userRepository.getUserById(userId, IListUserDto.class);
		return iListUserDtos;
	}

	@Override
	public Page<IListUserDto> getAllUsers(String userName, String pageNumber, String pageSize) {

		Page<IListUserDto> page;
		Pageable pageable = new Pagination().getPagination(pageNumber, pageSize);
		if ((userName == "") || (userName == null) || (userName.length() == 0)) {
			page = this.userRepository.findByOrderByIdDesc(pageable, IListUserDto.class);
		} else {

			page = userRepository.findByUserNameContainingIgnoreCase(userName, pageable, IListUserDto.class);

		}

		return page;
	}

	@Override
	public void deleteUserById(Long userId) throws Exception {
		this.userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
		this.userRepository.deleteById(userId);
	}

}

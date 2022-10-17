package com.jobportal.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jobportal.dto.OtpDtoForConformPassword;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;

public interface AuthInterface {

	public UserDto registerUser(UserDto userDto) throws Exception;

	public Boolean comparePassword(String password, String hashPassword);

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

	Boolean updateUserWithPassword(OtpDtoForConformPassword modelDto, UserEntity userEntity, OtpEntity otpEntity)
			throws Exception;
}

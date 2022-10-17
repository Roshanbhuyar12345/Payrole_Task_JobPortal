package com.jobportal.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.dto.OtpDtoForConformPassword;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.repository.AuthRepository;
import com.jobportal.repository.OtpRepository;
import com.jobportal.serviceInterface.AuthInterface;
import com.jobportal.utils.Validation;

@Service
public class AuthServiceImpl implements AuthInterface, UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthRepository authRepository;

	// @Autowired
	// private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(email);

		return new User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
	}

	@Override
	public UserDto registerUser(UserDto userDto) throws Exception {
		UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(userDto.getEmail().trim());
		if (Validation.isValidPassword(userDto.getPassword())) {
			if (userEntity != null) {
				throw new Exception("User already exist");
			}
			UserEntity entity = new UserEntity();
			entity.setUserName(userDto.getUserName());
			entity.setEmail(userDto.getEmail().trim());
			entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
			entity.setEducation(userDto.getEducation());
			entity.setExperience(userDto.getExperience());
			this.authRepository.save(entity);
			return userDto;
		} else {
			throw new Exception(
					"Password length should be 8 characters,1 capital lette, 1 special character, 1 numeric character");
		}

	}

	// for compare password
	public Boolean comparePassword(String password, String hashPassword) {

		return passwordEncoder.matches(password, hashPassword);

	}

	@Override
	public Boolean updateUserWithPassword(OtpDtoForConformPassword modelDto, UserEntity userEntity, OtpEntity otpEntity)
			throws Exception {
		userEntity.setPassword(passwordEncoder.encode(modelDto.getPassword()));
		this.authRepository.save(userEntity);
		this.otpRepository.delete(otpEntity);
		return true;

	}

}

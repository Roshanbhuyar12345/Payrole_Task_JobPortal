package com.jobportal.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.configuration.JwtTokenUtil;
import com.jobportal.dto.AuthResponceDto;
import com.jobportal.dto.ErrorResponceDto;
import com.jobportal.dto.JwtRequest;
import com.jobportal.dto.LoggerDto;
import com.jobportal.dto.OtpDtoForConformPassword;
import com.jobportal.dto.OtpDtoForForgetPassword;
import com.jobportal.dto.SuccessResponceDto;
import com.jobportal.dto.UserDto;
import com.jobportal.entity.OtpEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.repository.AuthRepository;
import com.jobportal.repository.OtpRepository;
import com.jobportal.serviceInterface.AuthInterface;
import com.jobportal.serviceInterface.EmailInterface;
import com.jobportal.serviceInterface.LoggerInterface;
import com.jobportal.serviceInterface.OtpInterface;
import com.jobportal.utils.ApisUrls;
import com.jobportal.utils.Validation;

@RestController
public class AuthController {
	@Autowired
	private AuthInterface authInterface;

	@Autowired
	private AuthRepository authRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoggerInterface loggerInterface;

	@Autowired
	private EmailInterface emailInterface;

	@Autowired
	private OtpInterface otpInterface;

	@Autowired
	private OtpRepository otpRepository;

	@PostMapping(ApisUrls.REGISTER)
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) throws Exception {
		try {

			this.authInterface.registerUser(userDto);
			return new ResponseEntity<>(new SuccessResponceDto("Registered successfully", "Successfull", userDto),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not registered"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(ApisUrls.LOGIN)
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtRequest createAuthenticationToken)
			throws Exception {
		try {
			UserEntity user = authRepository.findByEmailContainingIgnoreCase(createAuthenticationToken.getEmail());
			if (this.authInterface.comparePassword(createAuthenticationToken.getPassword(), user.getPassword())) {
				final UserDetails userDetails = this.authInterface
						.loadUserByUsername(createAuthenticationToken.getEmail());
				final UserEntity userEntity = this.authRepository
						.findByEmailContainingIgnoreCase(createAuthenticationToken.getEmail());
				final String token = this.jwtTokenUtil.generateToken(userDetails);
				// ArrayList<String> permissions =
				// authInterface.getUserPermission(user.getId());
				LoggerDto loggerDto = new LoggerDto();
				loggerDto.setToken(token);

				Calendar calendar = Calendar.getInstance();

				calendar.add(Calendar.HOUR_OF_DAY, 5);

				loggerDto.setExpireAt(calendar.getTime());

				this.loggerInterface.addTokenToLogger(loggerDto, userEntity);
				return new ResponseEntity<>(new SuccessResponceDto("Login successfull", "token", new AuthResponceDto(
						userEntity.getId(), userEntity.getUserName(), userEntity.getEmail(), token)), HttpStatus.OK);

			} else {
				return new ResponseEntity<>(new ErrorResponceDto("Invalid password", "Please enter valid password"),
						HttpStatus.BAD_REQUEST);

			}
		} finally {

		}
	}

	@PostMapping(ApisUrls.FORGOT_PASSWORD)
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody OtpDtoForForgetPassword otpDto,
			HttpServletRequest request) throws Exception {

		try {

			UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(otpDto.getEmail());

			if (null == userEntity) {
				return new ResponseEntity<>(new ErrorResponceDto("User not found", "userNotFound"),
						HttpStatus.BAD_REQUEST);
			}

			generateOtpAndSendEmail(otpDto.getEmail(), userEntity.getId());

			return new ResponseEntity<>(
					new SuccessResponceDto("OTP send user email", "OTP send succesfully", userEntity.getEmail()),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto("User not found", "Not found"), HttpStatus.BAD_REQUEST);

		}
	}

	@PutMapping(ApisUrls.FORGOT_PASSWORD_CONFORM)
	public ResponseEntity<?> createForgotPasswordcCnfirm(@Valid @RequestBody OtpDtoForConformPassword payload)
			throws Exception {

		try {
			UserEntity userEntity = this.authRepository.findByEmailContainingIgnoreCase(payload.getEmail());

			if (null == userEntity) {
				return new ResponseEntity<>(new ErrorResponceDto("Invalid email or otp", "invalidEmailOrOtp"),
						HttpStatus.BAD_REQUEST);
			}

			OtpEntity otpEntity = this.otpRepository.findByOtp(payload.getOtp());

			if (null == otpEntity) {
				return new ResponseEntity<>(new ErrorResponceDto("Invalid email or otp", "invalidEmailOrOtp"),
						HttpStatus.BAD_REQUEST);
			} else {
				if (!otpEntity.getEmail().equals(payload.getEmail())) {
					return new ResponseEntity<>(new ErrorResponceDto("Invalid email or otp", "invalidEmailOrOtp"),
							HttpStatus.BAD_REQUEST);
				}
			}

			if (!Validation.isValidPassword(payload.getPassword())) {
				return new ResponseEntity<>(new ErrorResponceDto(
						"Password should have Minimum 8 and maximum 50 characters, at least one uppercase letter, one lowercase letter, one number and one special character and no white spaces",
						"Password validation not done"), HttpStatus.BAD_REQUEST);
			}

			this.authInterface.updateUserWithPassword(payload, userEntity, otpEntity);

			return new ResponseEntity<>(new SuccessResponceDto("Password updated successfully", "successfull", "Done"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponceDto(e.getMessage(), "Not found"), HttpStatus.BAD_REQUEST);
		}
	}

	private void generateOtpAndSendEmail(String email, Long userId) throws Exception {

		final int otp = emailInterface.generateOTP();

		String otp1 = Integer.toString(otp);

		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.MINUTE, 5);

		this.otpInterface.saveOtp(email, otp, userId, calender.getTime());

		this.emailInterface.sendSimpleMessage(email, "Otp for forget password is : ", otp1);

	}
}

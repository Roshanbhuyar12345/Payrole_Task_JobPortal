package com.jobportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OtpDtoForConformPassword {

	@NotBlank(message = "email is Required*emailRequired")
	@NotEmpty(message = "email is Required*emailRequired")
	@NotNull(message = "email is Required*emailRequired")
	private String email;

	@NotNull(message = "otp is Required*otpRequired")
	private Integer otp;

	@NotBlank(message = "password is Required*passwordRequired")
	@NotEmpty(message = "password is Required*passwordRequired")
	@NotNull(message = "password is Required*passwordRequired")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public OtpDtoForConformPassword() {
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

}

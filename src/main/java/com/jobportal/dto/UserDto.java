package com.jobportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

	@NotBlank(message = "email is Required*emailRequired")
	@NotEmpty(message = "email is Required*emailRequired")
	@NotNull(message = "email is Required*emailRequired")
	private String email;

	@NotBlank(message = "userName is Required*userNameRequired")
	@NotEmpty(message = "userName is Required*userNameRequired")
	@NotNull(message = "userName is Required*userNameRequired")
	private String userName;

	@NotBlank(message = "password is Required*passwordRequired")
	@NotEmpty(message = "password is Required*passwordRequired")
	@NotNull(message = "password is Required*passwordRequired")
	private String password;

	@NotBlank(message = "education is Required*educationRequired")
	@NotEmpty(message = "education is Required*educationRequired")
	@NotNull(message = "education is Required*educationRequired")
	private String education;

	@NotBlank(message = "experience is Required*experienceRequired")
	@NotEmpty(message = "experience is Required*experienceRequired")
	@NotNull(message = "experience is Required*experienceRequired")
	private String experience;

	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public UserDto(String email, String userName, String password, String education, String experience) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.education = education;
		this.experience = experience;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

}

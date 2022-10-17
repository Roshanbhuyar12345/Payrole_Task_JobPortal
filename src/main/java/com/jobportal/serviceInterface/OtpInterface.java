package com.jobportal.serviceInterface;

import java.util.Date;

import com.jobportal.entity.OtpEntity;

public interface OtpInterface {

	public OtpEntity saveOtp(String email, Integer otp, Long userId, Date expiry) throws Exception;
}

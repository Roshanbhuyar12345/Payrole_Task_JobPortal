package com.jobportal.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.entity.OtpEntity;
import com.jobportal.repository.OtpRepository;
import com.jobportal.serviceInterface.OtpInterface;

@Service
public class OtpServiceImpl implements OtpInterface {

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public OtpEntity saveOtp(String email, Integer otp, Long userId, Date expiry) throws Exception {
		OtpEntity otpEntity = new OtpEntity();
		try {
			// this.otpRepository.deleteAllByEmail(email);

			otpEntity.setEmail(email);
			otpEntity.setOtp(otp);
			otpEntity.setExpireAt(expiry);
			otpEntity.setUserId(userId);
			this.otpRepository.save(otpEntity);
			return otpEntity;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return otpEntity;
	}
}

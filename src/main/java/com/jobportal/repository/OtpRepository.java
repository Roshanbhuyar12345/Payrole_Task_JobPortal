package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jobportal.entity.OtpEntity;

@Repository
public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

	void deleteAllByEmail(String email);

	void deleteAllByUserId(Long userId);

	OtpEntity findByOtp(Integer otp);

}

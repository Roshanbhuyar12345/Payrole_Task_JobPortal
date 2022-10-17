package com.jobportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.entity.LoggerEntity;

public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {

	LoggerEntity findByToken(String token);

}

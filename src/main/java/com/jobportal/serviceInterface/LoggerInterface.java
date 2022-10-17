package com.jobportal.serviceInterface;

import com.jobportal.dto.LoggerDto;
import com.jobportal.entity.LoggerEntity;
import com.jobportal.entity.UserEntity;

public interface LoggerInterface {

	LoggerDto addTokenToLogger(LoggerDto loggerDto, UserEntity userEntity);

	public LoggerEntity getLoggerDetail(String token);
}

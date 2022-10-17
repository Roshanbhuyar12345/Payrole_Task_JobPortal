package com.jobportal.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.dto.LoggerDto;
import com.jobportal.entity.LoggerEntity;
import com.jobportal.entity.UserEntity;
import com.jobportal.repository.LoggerRepository;
import com.jobportal.serviceInterface.LoggerInterface;

@Service
public class LoggerServiceImpl implements LoggerInterface {

	@Autowired
	private LoggerRepository loggerRepository;

	@Override
	public LoggerDto addTokenToLogger(LoggerDto loggerDto, UserEntity userEntity) {
		LoggerEntity entity = new LoggerEntity();

		entity.setToken(loggerDto.getToken());
		entity.setUserId(userEntity);
		entity.setExpireAt(loggerDto.getExpireAt());
		this.loggerRepository.save(entity);
		return loggerDto;
	}

	@Override
	public LoggerEntity getLoggerDetail(String token) {
		LoggerEntity loggerEntity;
		loggerEntity = this.loggerRepository.findByToken(token);
		return loggerEntity;
	}

}

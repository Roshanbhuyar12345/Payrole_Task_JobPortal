package com.jobportal.serviceInterface;

import javax.mail.MessagingException;

import com.jobportal.entity.UserEntity;

public interface EmailInterface {

	public void sendSimpleMessage(String emailTo, String subject, String text) throws MessagingException;

	public String sendMail(String emailTo, String subject, String text, UserEntity userEntity);

	public int generateOTP();
}

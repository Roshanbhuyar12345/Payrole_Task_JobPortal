
package com.jobportal.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jobportal.entity.UserEntity;
import com.jobportal.serviceInterface.EmailInterface;

@Service
public class EmailService implements EmailInterface {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendSimpleMessage(String emailTo, String subject, String text) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("noreplyntts@gmail.com");
		helper.setTo(emailTo);
		helper.setSubject(subject);
		helper.setText(text, true);
		javaMailSender.send(message);
	}

	@Override
	public String sendMail(String emailTo, String subject, String text, UserEntity userEntity) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("noreplyntts@gmail.com");
		simpleMailMessage.setTo(userEntity.getEmail());
		simpleMailMessage.setSubject("Apply sucessfully");
		simpleMailMessage.setText("Text demo");
		javaMailSender.send(simpleMailMessage);
		return "Email Send";
	}

	@Override
	public int generateOTP() {

		int min = 100000;
		int max = 999999;

		int randomInt = (int) Math.floor(Math.random() * (max - min + 1) + min);
		return randomInt;

	}

}

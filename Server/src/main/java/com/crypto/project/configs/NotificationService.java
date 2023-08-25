package com.crypto.project.configs;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//	@Autowired
//	public void NotificationService(JavaMailSender javamailSender) {
//		this.javaMailSender = javamailSender;
//	}
	
	public String sendNotification(String sendTo,
									String subject,
									String text) throws MessagingException {
		
	MimeMessage message = javaMailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setFrom("securedworld3@gmail.com");
		helper.setTo(sendTo);
		helper.setText(text ,true);
		helper.setSubject(subject);
		javaMailSender.send(message);
		System.out.println("mailSend");
		return "Mail Send";
		
	}

}

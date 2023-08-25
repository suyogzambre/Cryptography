package com.crypto.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.project.configs.NotificationService;
import com.crypto.project.model.SupportForm;
import com.crypto.project.model.Users;
import com.crypto.project.service.SupportService;
import com.crypto.project.service.UsersRepository;

@RequestMapping("/support")
@RestController
public class SupportController {

	@Autowired
	private SupportService supportRepo;
	@Autowired
	public NotificationService sendMail;
	@Autowired
	//private UsersService usersService;
	private UsersRepository userRepository;

	@PostMapping("/getInqury")
	@CrossOrigin("*")
	private ResponseEntity<Object> getInq(@RequestBody SupportForm obj) {
		supportRepo.save(obj);
		
		try {
			String mailBody = "<html>\r\n"
					+ "<body>\r\n"
					+ "<h4>Hello Dear,</h4>\r\n"
					+ "<p>\r\n"
					+ "\r\n"
					+ "Thank you for reaching Secured World! <br>\r\n"
					+ "We have received your request.</p>\r\n"
					+ "<p>\r\n"
					+ "Our Service Associate will review the request and connect with you in the next 24 hours., <br>\r\n"
					+ "\r\n"
					+ "Due to heavy traffic on our website, it may take a little longer than usual for us to reach you.<br>\r\n"
					+ "We regret the inconvenience and appreciate your understanding.\r\n"
					+ "</p>\r\n"
					+ "<p>\r\n"
					+ "Thank you!\r\n"
					+ "<br>\r\n"
					+ "Sincerely,<br>\r\n"
					+ "Sercured World Team<br>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			
			sendMail.sendNotification(obj.getEmail(), "Secured World : Regarding Your Query", mailBody);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// TODO: handle exception
			return  null;
		}
	
	}
	
	@PostMapping("/getSupport")
	private ResponseEntity<Object> getSupport(@RequestBody SupportForm obj) {
		
		Users user = userRepository.findByUserNumId(obj.getId());
		obj.setMobileNo(user.getMobileNo());
		System.out.println(obj.getMobileNo());
		obj.setEmail(user.getEmailId());
		obj.setId(0);
		supportRepo.save(obj);
		try {
			String mailBody = "<html>\r\n"
					+ "<body>\r\n"
					+ "<h4>Hello Dear," + user.getCompleteName()+ "</h4>\r\n"
					+ "<p>\r\n"
					+ "\r\n"
					+ "Thank you for reaching Secured World! <br>\r\n"
					+ "We have received your request.</p>\r\n"
					+ "<p>\r\n"
					+ "Our Service Associate will review the request and connect with you in the next 24 hours., <br>\r\n"
					+ "\r\n"
					+ "Due to heavy traffic on our website, it may take a little longer than usual for us to reach you.<br>\r\n"
					+ "We regret the inconvenience and appreciate your understanding.\r\n"
					+ "</p>\r\n"
					+ "<p>\r\n"
					+ "Thank you!\r\n"
					+ "<br>\r\n"
					+ "Sincerely,<br>\r\n"
					+ "Secured World Team.<br>\r\n"
					+ "</body>\r\n"
					+ "</html>";
			
			sendMail.sendNotification(obj.getEmail(), "Secured World : Regarding Your Qurry", mailBody);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// TODO: handle exception
			return  null;
		}
		
		
	}
	
}

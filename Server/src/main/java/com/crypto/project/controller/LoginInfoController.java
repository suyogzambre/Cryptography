package com.crypto.project.controller;
import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.project.model.LoginInfo;
import com.crypto.project.service.LoginInfoRepository;;


@RestController
public class LoginInfoController {

	@Autowired
	//private UsersService usersService;
	private LoginInfoRepository loginInfoRepository;

	
	@PostMapping("/users/login/{userId}")
	public ResponseEntity<LoginInfo> login(@RequestBody LoginInfo loginInfo, @PathVariable String userId) {

		Optional<LoginInfo> userOptional = loginInfoRepository.findById(userId);

		if (!userOptional.isPresent())
		{
			//return null;
			return ResponseEntity.status(400).body(null);
		}
		else {
			
			LoginInfo loginInfoLocal = userOptional.get();
			if(loginInfoLocal.getPassword().endsWith(loginInfo.getPassword()));
			{
				loginInfoLocal.setLogin_date_time(new Date(0));
				loginInfoLocal.setLoginFlag(true);
				loginInfoLocal.setJwt_toekn("jwttoken");
				loginInfoLocal.setReferesh_token("refeshtoken");
				loginInfoRepository.save(loginInfoLocal);
				loginInfoLocal.setPassword("xxxxxxxxxx");
				
			return ResponseEntity.status(200).body(loginInfoLocal);
			}
			
		}

			//return ResponseEntity.notFound().build();

	}
	
}

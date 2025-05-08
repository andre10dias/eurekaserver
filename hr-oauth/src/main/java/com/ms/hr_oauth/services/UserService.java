package com.ms.hr_oauth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ms.hr_oauth.entities.User;
import com.ms.hr_oauth.feignclients.UserFeignClient;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFeignClient userFeignClient;
	
	public ResponseEntity<User> findByEmail(String email) {
		ResponseEntity<User> obj = userFeignClient.findByEmail(email);
		if (obj == null) {
			logger.error("E-mail not found.");
			return ResponseEntity.notFound().build();
		}
		
		logger.info("E-mail found: " + obj.getBody().getEmail());
		return ResponseEntity.ok().body(obj.getBody());
	}

}

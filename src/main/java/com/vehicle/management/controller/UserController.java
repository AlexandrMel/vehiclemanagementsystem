package com.vehicle.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.management.exception.RecordNotFoundException;
import com.vehicle.management.model.UserEntity;
import com.vehicle.management.model.dto.UserDetails;
import com.vehicle.management.service.UserService;
import com.vehicle.management.util.Validation;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/{userId}")
	@Operation(operationId = "Find User", summary = "Find Users using user Id")
	public ResponseEntity<UserEntity> getUser(@PathVariable("userId") Long userId) throws RecordNotFoundException {
		log.info("Entered into getUser()");
		UserEntity entity = userService.getUser(userId);
		
		return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/createusers")
	@Operation(operationId = "Create Users", summary = "Create Users for Vehicle Management System")
	public UserEntity createUser(@RequestBody UserDetails userDetails, BindingResult result) {
		Validation.validateCreateUserRequest(userDetails);
		log.info("Entered into createUser()");
		return userService.createUser(userDetails);
	}
}
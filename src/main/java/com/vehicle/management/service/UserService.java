package com.vehicle.management.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vehicle.management.exception.RecordNotFoundException;
import com.vehicle.management.model.UserEntity;
import com.vehicle.management.model.dto.UserDetails;
import com.vehicle.management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(VehicleService.class);

	public UserEntity getUser(Long userId) throws RecordNotFoundException {

		log.info("Entered into getUser()");

		Optional<UserEntity> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get();
		} else {
			throw new RecordNotFoundException("No user exist for given userId");
		}
	}

	public UserEntity createUser(UserDetails userDetails) {
		log.info("Entered into createUser()");
		UserEntity userEntity = new UserEntity();
		userEntity.setName(userDetails.getName());
		userEntity.setAddress(userDetails.getAddress());
		userEntity.setEmail(userDetails.getEmail());
		userEntity.setPhone(userDetails.getPhone());

		return userRepository.save(userEntity);
	}

}
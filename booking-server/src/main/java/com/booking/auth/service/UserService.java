package com.booking.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.auth.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	

}

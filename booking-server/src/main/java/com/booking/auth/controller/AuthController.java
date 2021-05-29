package com.booking.auth.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.model.User;
import com.booking.auth.repository.UserRepository;
import com.booking.globalExeption.AlreadyExist;
import com.booking.globalExeption.NotFoundException;
import com.booking.request.JwtResponse;
import com.booking.request.LoginForm;
import com.booking.request.SignUpForm;
import com.booking.util.JWTHelper;



@RestController
public class AuthController {

	@Autowired
	JWTHelper jwtHelper;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) throws Exception {

		Optional<User> user=userRepository.findByUsername(loginRequest.getUsername());
		
		if(!user.isPresent()) {
			throw new NotFoundException("username not exist");
		}
		if(!userRepository.existsByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword())) {
			throw new NotFoundException("password is wrong");
		}


		String jwt = jwtHelper.generateJWT(user.get().getUsername(), user.get());
	
		return ResponseEntity.ok().body(new JwtResponse(jwt,user.get().getUsername(),user.get().getRole()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			/*
			 * return new ResponseEntity<>(new
			 * ResponseMessage("Fail -> Username is already taken!"),
			 * HttpStatus.BAD_REQUEST);
			 */
			
				throw new  AlreadyExist("Username is already taken!!");
			
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			/*
			 * return new ResponseEntity<>(new
			 * ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);
			 */
			try {
				throw new AlreadyExist("Email is already taken!!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Creating user's account
		

		

	User user=new User(signUpRequest.getName(), signUpRequest.getUsername() , signUpRequest.getEmail(), signUpRequest.getPassword(),signUpRequest.getRole());

		userRepository.save(user);
		
		return  ResponseEntity.ok().body("User registered successfully!");
	}
}

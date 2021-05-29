package com.booking.hotel.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.auth.model.User;
import com.booking.globalExeption.NotFoundException;
import com.booking.hotel.model.Hotel;
import com.booking.hotel.service.HotelService;
import com.booking.request.JwtResponse;
import com.booking.request.LoginForm;

@RestController
@RequestMapping("hotel")
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@PostMapping("")
	public ResponseEntity<?> addHotel(@Valid @RequestBody Hotel hotelRequest) throws Exception {
		return ResponseEntity.ok().body(hotelService.addHotel(hotelRequest));
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllHotel() throws Exception {
		return ResponseEntity.ok().body(hotelService.getAllHotel());
	}

}

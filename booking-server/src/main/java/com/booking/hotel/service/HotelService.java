package com.booking.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.model.Hotel;
import com.booking.hotel.repository.HotelRepository;

@Service
public class HotelService {

	@Autowired
	HotelRepository hotelRepository;
	
	
	public Hotel addHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
	
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}
}

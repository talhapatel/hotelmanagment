package com.booking.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.hotel.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long>{

}

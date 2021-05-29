package com.booking.review.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.booking.auth.model.User;
import com.booking.hotel.model.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String comment;
	private Long rating;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="HOTEL_ID")
	Hotel hotel;
	
	@ManyToOne(fetch=FetchType.EAGER) 	
 	@JoinColumn(name="USER_ID")
 	User user;
	
	
}

package com.booking.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

	private String token;
	private String userName;
	private String role;
	
}

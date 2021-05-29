package com.booking.util;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTHelper {
	
	public String generateJWT(String subject, Object claim){
		return  Jwts.builder().setSubject(subject).claim("user", claim)
		.setIssuedAt(new Date())
		.setExpiration(null)
        .signWith(SignatureAlgorithm.HS256, com.booking.constant.Constants.SECRET_KEY).compact();
	}
	

}
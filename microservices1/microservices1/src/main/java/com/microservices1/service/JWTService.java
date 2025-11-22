package com.microservices1.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    private static final String SECRET_KEY = "mySecretKey";
    private static final long EXPIRATION_TIME = 8640000;

    public String validateTokenAndRetrieveSubject(String token){
        return JWT.require(Algorithm
                        .HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();
    }
}




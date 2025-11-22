package com.microservices1.filter;

import com.authservice.service.CustomerUserDetailsService;
import com.microservices1.client.UserClient;
import com.microservices1.dto.User;
import com.microservices1.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserClient userClient;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String rawToken = token.substring(7);
            String username = jwtService.validateTokenAndRetrieveSubject(rawToken);
            User user =userClient.getUserByName(username,rawToken);
            var authToken = new UsernamePasswordAuthenticationToken(
                    user, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_" +user.getRoles())));

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }


        filterChain.doFilter(request, response);


    }
}

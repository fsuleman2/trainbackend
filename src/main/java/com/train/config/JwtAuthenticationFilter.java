package com.train.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.train.service.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//taking auth header
		final String requestTokenHeader = request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken = null;
		if(requestTokenHeader!=null&& requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username=this.jwtUtils.extractUsername(jwtToken);
			}catch(ExpiredJwtException e) {
				
				System.out.println(e.getMessage());
				System.out.println("Token has Expired bro, generate once again");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		else {
			//logger
			System.out.println("Token Invalid, not starts with bearer im from authfilter");
		}
		//once we get the token we validate it
		//if validated
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			//taking user details service form db
			final UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			if(this.jwtUtils.validateToken(jwtToken, userDetails)) {
				//token is valid then set auth in context
				UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				 usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
			}
		}
		else {
			System.out.println("Token is not valid bro!!!,Kia kar rahe hoo?");
		}
		filterChain.doFilter(request, response);
	}

}

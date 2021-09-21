package com.train.model;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority{
	private String authority; 
	@Override
	public String getAuthority() {
		
		return this.authority;
	}
	
}

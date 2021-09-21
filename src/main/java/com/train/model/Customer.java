package com.train.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customer implements UserDetails{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long cId;
private String name;
private String email;
private String username;
private String password;
private String phone;
private String address;
private String aadhar;
private boolean enabled = true;
private String profile;
public Customer() {
	// TODO Auto-generated constructor stub
}

public Customer(Long cId, String name, String email, String username, String password, String phone, String address,
		String aadhar, boolean enabled, String profile, Set<CustomerRole> customerRoles) {
	super();
	this.cId = cId;
	this.name = name;
	this.email = email;
	this.username = username;
	this.password = password;
	this.phone = phone;
	this.address = address;
	this.aadhar = aadhar;
	this.enabled = enabled;
	this.profile = profile;
	this.customerRoles = customerRoles;
}

////Customer can have many roles
@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "customer")
@JsonIgnore
private Set<CustomerRole> customerRoles = new HashSet<>();
public Long getcId() {
	return cId;
}

public void setcId(Long cId) {
	this.cId = cId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getAadhar() {
	return aadhar;
}

public void setAadhar(String aadhar) {
	this.aadhar = aadhar;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public String getProfile() {
	return profile;
}

public void setProfile(String profile) {
	this.profile = profile;
}

public Set<CustomerRole> getCustomerRoles() {
	return customerRoles;
}

public void setCustomerRoles(Set<CustomerRole> customerRoles) {
	this.customerRoles = customerRoles;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	Set<Authority> set = new HashSet<>();
	this.customerRoles.forEach(customerRole -> {
		set.add(new Authority(customerRole.getRole().getRoleName()));
	});
	return set;
}

@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}

}

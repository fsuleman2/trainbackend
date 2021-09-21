package com.train.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role {
	@Id
	private Long roleId;
	private String roleName;
	public Role() {
		// TODO Auto-generated constructor stub
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Set<CustomerRole> getCustomerRole() {
		return customerRole;
	}
	public void setCustomerRole(Set<CustomerRole> customerRole) {
		this.customerRole = customerRole;
	}
	public Role(Long roleId, String roleName, Set<CustomerRole> customerRole) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.customerRole = customerRole;
	}
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	private Set<CustomerRole> customerRole = new HashSet<>();
}

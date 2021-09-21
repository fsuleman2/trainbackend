package com.train.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}

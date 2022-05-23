package com.inti.SpringBootValidationThymeleaf.repository;

import com.inti.SpringBootValidationThymeleaf.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}

package com.inti.SpringBootValidationThymeleaf.repository;

import com.inti.SpringBootValidationThymeleaf.model.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	@Query(value = "select r.* from utilisateur u, utilisateur_role ur, role r\r\n"
			+ "where u.id = ur.id_utilisateur and ur.id_role = r.id\r\n"
			+ "and (u.username=:username or u.email=:username);", nativeQuery = true)
	List<Role> findRolesByUsername(@Param("username") String username);

}

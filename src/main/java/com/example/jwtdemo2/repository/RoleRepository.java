package com.example.jwtdemo2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.jwtdemo2.models.ERole;
import com.example.jwtdemo2.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	@Query(value =" INSERT INTO roles(name) VALUES('ROLE_USER')", nativeQuery = true)
	public void in();
}

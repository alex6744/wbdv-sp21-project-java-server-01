package com.example.jwtdemo2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jwtdemo2.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value="SELECT * FROM users WHERE id=:id", nativeQuery = true)
	public User findUserById(@Param("id") Long Id);

	@Query(value="UPDATE users SET email=:email WHERE id=:id", nativeQuery = true)
	public int updateUserById(@Param("id") Long Id,String email);
}

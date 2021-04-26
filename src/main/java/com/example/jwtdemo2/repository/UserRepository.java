package com.example.jwtdemo2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.jwtdemo2.models.User;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query(value="SELECT * FROM users WHERE id=:id", nativeQuery = true)
	public User findUserById(@Param("id") Long Id);

	@Modifying
	@Query(value = "insert into creator (id,company) VALUES (:id,:company)", nativeQuery = true)
	@Transactional
	void insertCreator( @Param("id") Long id,@Param("company") String company);

	@Modifying
	@Query(value = "update users set email=:email where id=:id", nativeQuery = true)
	@Transactional
	void updateUser( @Param("id") Long id,@Param("email") String email);
}

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



	@Modifying
	@Query(value = "insert into creator (id,company) VALUES (:id,:company)", nativeQuery = true)
	@Transactional
	void insertCreator( @Param("id") Long id,@Param("company") String company);

	@Modifying
	@Query(value = "insert into admin (id,passcode) VALUES (:id,:passcode)", nativeQuery = true)
	@Transactional
	void insertAdmin( @Param("id") Long id,@Param("passcode") String passcode);

	@Modifying
	@Query(value = "update users set email=:email where id=:id", nativeQuery = true)
	@Transactional
	void updateUserEmail( @Param("id") Long id,@Param("email") String email);

	@Modifying
	@Query(value = "update users set first_name=:firstname where id=:id", nativeQuery = true)
	@Transactional
	void updateUserFirst( @Param("id") Long id,@Param("firstname") String firstname);

	@Modifying
	@Query(value = "update users set last_name=:lastname where id=:id", nativeQuery = true)
	@Transactional
	void updateUserLast( @Param("id") Long id,@Param("lastname") String lastname);
}

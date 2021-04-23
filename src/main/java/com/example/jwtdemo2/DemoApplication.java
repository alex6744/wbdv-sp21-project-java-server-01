package com.example.jwtdemo2;


import com.example.jwtdemo2.repository.RoleRepository;
import com.example.jwtdemo2.services.RatingService;
import com.example.jwtdemo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	@Autowired
	static RatingService service;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);




	}

}

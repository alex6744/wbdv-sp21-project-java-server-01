package com.example.jwtdemo2;


import com.example.jwtdemo2.models.ERole;
import com.example.jwtdemo2.repository.RoleRepository;
import com.example.jwtdemo2.services.RatingService;
import com.example.jwtdemo2.services.RoleService;
import com.example.jwtdemo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.swing.text.html.parser.Entity;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);




	}
	@Bean
	public CommandLineRunner demoData(RoleService service){
		if(service.findByEROLE(ERole.ROLE_ADMIN)){
			return null;
		}else {
			return args -> {
				service.createRole(ERole.ROLE_ADMIN);
				service.createRole(ERole.ROLE_USER);
				service.createRole(ERole.ROLE_CREATOR);
			};
		}
	}

}

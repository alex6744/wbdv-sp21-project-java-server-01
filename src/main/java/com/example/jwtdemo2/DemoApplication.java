package com.example.jwtdemo2;


import com.example.jwtdemo2.models.ERole;
import com.example.jwtdemo2.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

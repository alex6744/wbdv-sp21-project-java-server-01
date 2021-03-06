package com.example.jwtdemo2.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.example.jwtdemo2.models.*;
import com.example.jwtdemo2.repository.CreatorRepository;
import com.example.jwtdemo2.services.MovieRatingService;
import com.example.jwtdemo2.services.TvRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.jwtdemo2.payload.request.LoginRequest;
import com.example.jwtdemo2.payload.request.SignupRequest;
import com.example.jwtdemo2.payload.response.JwtResponse;
import com.example.jwtdemo2.payload.response.MessageResponse;
import com.example.jwtdemo2.repository.RoleRepository;
import com.example.jwtdemo2.repository.UserRepository;
import com.example.jwtdemo2.security.jwt.JwtUtils;
import com.example.jwtdemo2.security.services.UserDetailsImpl;
import javax.servlet.http.HttpSession;
//part of code credit from
//https://bezkoder.com/spring-boot-jwt-authentication/#google_vignette



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CreatorRepository creatorRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	TestController controller;

   @Autowired
   MovieRatingService movieRatingService;

   @Autowired
	TvRatingService tvRatingService;



	@GetMapping ("/movierating/{mid}")
	public List<rateDemo> findRatingByMovieId(@PathVariable("mid") Integer mid){
		Long l = new Long(mid);
		List<MovieRating> rates= movieRatingService.findRatingByMovieId(l);
		List<rateDemo> ratings=new ArrayList<>();
		for (MovieRating r:rates){
			ratings.add(new rateDemo(r.getRatingId(),r.getComment(),r.getRate(),r.getUser().getUsername(),r.getMovie().getMovieId()));
		}
		return ratings;
	}

	@GetMapping ("/tvrating/{tid}")
	public List<rateDemo> findRatingByTvId(@PathVariable("tid") Integer tid){
		Long l = new Long(tid);
		List<TvRating> rates= tvRatingService.findRatingByTvId(l);
		List<rateDemo> ratings=new ArrayList<>();
		for (TvRating r:rates){
			ratings.add(new rateDemo(r.getRatingId(),r.getComment(),r.getRate(),r.getUser().getUsername(),r.getTvId()));
		}
		return ratings;
	}
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpSession session) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		Set<Role> r=new HashSet<>();
		for(String s:roles){
			Role role;
			switch (s){

				case "ROLE_ADMIN":
					role=new Role(ERole.ROLE_ADMIN);
					break;
				case "ROLE_CREATOR":
					role=new Role(ERole.ROLE_CREATOR);
					break;
				default:
					role=new Role(ERole.ROLE_USER);

			}
			r.add(role);
		}

		User u=new User(userDetails.getId(),userDetails.getUsername(),
				userDetails.getEmail(),r);

		session.setAttribute("currentUser", u);
		session.setAttribute("token", jwt);
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							signUpRequest.getFirstname(),signUpRequest.getLastname());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;

				case "creator":
					Role creatorRole = roleRepository.findByName(ERole.ROLE_CREATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(creatorRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		for(String s:strRoles){
			if(s.equals("admin")){
				Long id=userRepository.findByUsername(signUpRequest.getUsername()).get().getId();
				userRepository.insertAdmin(id,signUpRequest.getPasscode());
			}else if(s.equals("creator")){
				Long id=userRepository.findByUsername(signUpRequest.getUsername()).get().getId();
				userRepository.insertCreator(id,signUpRequest.getCompany());
			}
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

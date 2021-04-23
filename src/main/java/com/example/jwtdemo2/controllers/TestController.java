package com.example.jwtdemo2.controllers;

import com.example.jwtdemo2.models.Rating;
import com.example.jwtdemo2.models.User;
import com.example.jwtdemo2.services.RatingService;
import com.example.jwtdemo2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/")
public class TestController {
	private Long id;
	private String username;

	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/profile")
	public User profile(HttpSession session) {
		User currentUser = (User)
				session.getAttribute("currentUser");
		return currentUser;
	}

	@GetMapping("/lastsignin")
	public String current() {
		return username;
	}

	@PutMapping("/{id}")
	public int updateUser(@PathVariable("id") Integer id, @RequestBody User user){

		Long l = new Long(id);
		return userService.updateUserById(l,user);
	}

	@PostMapping("/user/{uid}/rating")
	public Rating createRating  (@PathVariable("uid") Integer id,@RequestBody Rating rate){
		Long l = new Long(id);
		return ratingService.createRating(l,rate);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User findUserById(@PathVariable("id") Long id){
		return userService.findUserById(id);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<String >userAccess() {
		List<String> a=new ArrayList<>();
		a.add("sddsasd");
		a.add("sdd");
		return a;
	}
//"User Content."
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

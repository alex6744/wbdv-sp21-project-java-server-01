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
@RequestMapping("/api/controller")
public class TestController {
	private Long id;
	private String username;

	@Autowired
	UserService userService;

	@Autowired
	RatingService ratingService;


	@GetMapping("/signout")
	public void logout
			(HttpSession session) {
		session.invalidate();
	}

	@GetMapping("/currentUser")
	public List<Object> currentUser(HttpSession session) {
		List<Object> c=new ArrayList<>();

			c.add(session.getAttribute("token"));
			c.add((Object) ((User) session.getAttribute("currentUser")).getUsername());


		return c;
	}

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}



	@GetMapping("/lastsignin")
	public String current() {
		return username;
	}

	@GetMapping("/profile/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public Optional<User> profile(@PathVariable("id") Integer id) {
		Long l = new Long(id);

		return userService.findUserById(l);
	}

	@PutMapping("/profile/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public int updateUser(@PathVariable("id") Integer id, @RequestBody User user){

		Long l = new Long(id);
		if(userService.existsEmail(user.getEmail())){
			return 0;
		}else {
			if(user.getEmail()==null){
				return userService.updateUserById(l, user,0);
			}else {
				return userService.updateUserById(l, user,1);
			}
		}

	}

	@PostMapping("/user/{uid}/rating")
	public Rating createRating  (@PathVariable("uid") Integer id,@RequestBody Rating rate){
		Long l = new Long(id);
		return ratingService.createRating(l,rate);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User findUserById(@PathVariable("id") Long id){
		return null;//userService.findUserById(id);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
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

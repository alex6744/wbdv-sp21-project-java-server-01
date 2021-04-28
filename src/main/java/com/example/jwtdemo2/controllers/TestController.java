package com.example.jwtdemo2.controllers;

import com.example.jwtdemo2.models.*;
import com.example.jwtdemo2.repository.MovieRepository;
import com.example.jwtdemo2.services.MovieService;
import com.example.jwtdemo2.services.MovieRatingService;
import com.example.jwtdemo2.services.TvRatingService;
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

	@Autowired
	MovieRepository movieRepository;
	@Autowired
	UserService userService;

	@Autowired
	MovieRatingService movieRatingService;

	@Autowired
	MovieService movieService;

	@Autowired
	TvRatingService tvRatingService;

	@GetMapping ("/user/{uid}/findmovie")
	@PreAuthorize("hasRole('CREATOR')")
	public List<Movie> findUploadMovie(@PathVariable("uid") Integer uid){
		Long l=new Long(uid);
		List<UploadMovie> up=movieService.findById(l);
		List<Movie> result=new ArrayList<>();
		for(UploadMovie u:up){
			result.add(movieRepository.findByMovieId(u.getMovieId()).get());
		}
		return  result;
	}

	@GetMapping ("/movieRating/{uid}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public List<rateDemo> findRatingByMovieId(@PathVariable("uid") Integer uid){
		Long l = new Long(uid);
		List<MovieRating> rates= movieRatingService.findRatingById(l);
		List<rateDemo> ratings=new ArrayList<>();
		for (MovieRating r:rates){
			ratings.add(new rateDemo(r.getRatingId(),r.getComment(),r.getRate(),r.getUser().getUsername(),r.getMovie().getMovieId()));
		}
		return ratings;
	}
	@GetMapping ("/tvRating/{uid}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public List<rateDemo> findRatingByTvId(@PathVariable("uid") Integer uid){
		Long l = new Long(uid);
		List<TvRating> rates= tvRatingService.findRatingById(l);
		List<rateDemo> ratings=new ArrayList<>();
		for (TvRating r:rates){
			ratings.add(new rateDemo(r.getRatingId(),r.getComment(),r.getRate(),r.getUser().getUsername(),r.getTvId()));
		}
		return ratings;
	}

	@GetMapping("/findAllUser")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> findAllUser(){
		return userService.findAllUser();
	}

	@PostMapping("/deleteMovieRating/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public int deleteMovieRating(@PathVariable("id") Integer id){



		return movieRatingService.deleteMovieRating(id);
	}

	@PostMapping("/deleteTvRating/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public int deleteTvRating(@PathVariable("id") Integer id){



		return tvRatingService.deleteTvRating(id);
	}


	@PostMapping("/user/{uid}/upload")
	@PreAuthorize("hasRole('CREATOR')")
	public int uploadMovie(@PathVariable("uid")Integer uid,@RequestBody Movie movie){
		Long movieId=(new Date().getTime());
		Long l = new Long(uid);
		movie.setMovieId(movieId);

		return movieService.insertUpload(movieId,l,movie);
	}

	@PostMapping("/user/{uid}/movierating/{mid}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public int createMovieRating  (@PathVariable("uid") Integer id,@PathVariable("mid") Integer mid,@RequestBody MovieRating rate){
		Long l = new Long(id);
		Long movieId=new Long(mid);
		if(!movieService.existsMovieById(movieId)){
			movieService.createMovie(movieId,new Movie());
		}
		return movieRatingService.createMovieRating(l,movieId,rate);
	}

	@PostMapping("/user/{uid}/tvrating/{tid}")
	@PreAuthorize("hasRole('USER') or hasRole('CREATOR') or hasRole('ADMIN')")
	public int createTvRating  (@PathVariable("uid") Integer id, @PathVariable("tid") Integer tid, @RequestBody TvRating rate){
		Long l = new Long(id);
		Long tvId=new Long(tid);

		return tvRatingService.createTvRating(l,tvId,rate);
	}


	@GetMapping("movie/{id}")

	public boolean existsMovie(@PathVariable("id") Integer id) {
		Long l = new Long(id);

		return movieService.existsMovieById(l);
	}
	@PostMapping("movie/{id}")
	public Movie createMovie(@PathVariable("id") Integer id) {
		Long l = new Long(id);

		return movieService.createMovie(l,new Movie());
	}

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


	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public User findUserById(@PathVariable("id") Long id){
		return null;//userService.findUserById(id);
	}






}

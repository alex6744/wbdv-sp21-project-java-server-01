package com.example.jwtdemo2.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(	name = "users",
		uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
		})
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String username;


	private String email;


	private String password;

	private String firstName;
	private String lastName;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	Set<OrderedMovie> orderedMovie;

	@OneToMany(mappedBy = "user")
	Set<MovieRating> movieRating;

	@OneToMany(mappedBy = "user")
	Set<TvRating> tvRating;


	public User() {
	}

	public User(Long id) {
		this.id = id;
	}

	public User(User u) {
		this.id = u.getId();
		this.username = u.getUsername();
		this.email = u.getEmail();
		this.firstName=u.getFirstName();
		this.lastName=u.getLastName();
		this.roles = u.getRoles();
	}

	public User(Long id, String username, String email, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;

		this.roles = roles;
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User(String username, String email, String password, String firstName, String lastName) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;

	}

	public User(String username, String email , Set<Role> roles) {
		this.username = username;
		this.email = email;

		this.roles = roles;
	}

	public Set<OrderedMovie> getOrderedMovie() {
		return orderedMovie;
	}

	public void setOrderedMovie(Set<OrderedMovie> orderedMovie) {
		this.orderedMovie = orderedMovie;
	}

	public Set<MovieRating> getRating() {
		return movieRating;
	}

	public void setRating(Set<MovieRating> movieRating) {
		this.movieRating = movieRating;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

package com.epam.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.users.User;
import com.epam.model.users.UserProfileType;
import com.epam.service.UserService;

@RestController
public class UserRestContoller {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Endpoint to get roles granted to user
	 *
	 * @param user
	 *            the employee principal information(username)
	 */
	@RequestMapping(value = "/userRoles", method = RequestMethod.GET)
	public Collection<SimpleGrantedAuthority> userRoles() {
		
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		
		return authorities;
	}

	/**
	 * Endpoint that provides UI with data about all users
	 *
	 * @return list of all registered users
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> users() {
		
		List<User> users = userService.findAllUsers();
		return users;
	}

	/**
	 * Endpoint that returns a list of all possible user roles
	 * 
	 * @return list of all roles
	 */
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public List<UserProfileType> roles() {
		
		return Arrays.asList(UserProfileType.values());
	}

	/**
	 * Endpoint to register new users in the system
	 * 
	 * @param user
	 *            containts all essential data to add new user to database
	 * @return status code, 400 if user with such username already exists
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		
		if (!userService.isUsernameUnique(user.getId(), user.getUsername())) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"error\": \"User with such username already exists\"}");
		} else {
			
			userService.saveUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

	/**
	 * Endpoint to update users data in the system
	 * 
	 * @param user
	 *            contains updated information about user
	 */
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public void updateUser(@RequestBody User user) {
		
		userService.updateUser(user);
	}

	/**
	 * Enpoint to delete existing user from system
	 * 
	 * @param username
	 *            that points to user to delete
	 */
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam("username") String username) {
		
		userService.deleteUserByUsername(username);
	}
}

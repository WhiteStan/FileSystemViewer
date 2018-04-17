package com.epam.service;

import java.util.List;

import com.epam.model.users.User;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Defines methods for processing requests associated with {@link User}
 */
public interface UserService {

	/**
	 * Method to retrieve information about user by certain id
	 * 
	 * @param id uniquely specifies user
	 * @return instance of {@link User}
	 */
	User findById(Integer id);

	/**
	 * Method to retrieve information about user by certain username
	 * 
	 * @param username uniquely specifies user
	 * @return instance of {@link User}
	 */
	User findByUsername(String username);

	/**
	 * Method to add new user to the system
	 * 
	 * @param user is the instance of {@link User} to be saved
	 */
	void saveUser(User user);

	/**
	 * Method to update existing user in the system
	 * 
	 * @param user is the instance of {@link User} with updated information
	 */
	void updateUser(User user);

	/**
	 * Method to delete existing user in the system
	 * 
	 * @param username uniquely specifies user
	 */
	void deleteUserByUsername(String username);

	/**
	 * Method to retrieve all users in the system
	 * 
	 * @return list of {@link User}
	 */
	List<User> findAllUsers();

	/**
	 * Method to check whether user with such username and id is exists in the system
	 * 
	 * @param id
	 * @param username
	 * @return result of check
	 */
	boolean isUsernameUnique(Integer id, String username);
}

package com.epam.dao;

import java.util.List;

import com.epam.model.users.User;

/**
 * @author Stanislau_Makouski
 *
 * Interface specifies methods for work with {@link User}
 */
public interface UserDao {

	/**
	 * Method to find User by id
	 * 
	 * @param id of a user to find
	 * @return {@link User}
	 */
	User findById(Integer id);

	/**
	 * Method to find user by username
	 * 
	 * @param username of a user to find
	 * @return {@link User}
	 */
	User findByUsername(String username);

	/**
	 * Method to save user into the system
	 * 
	 * @param user is the instance of {@link User}
	 */
	void save(User user);

	/**
	 * Method to delete user from the system
	 * 
	 * @param username of user to delete
	 */
	void deleteByUsername(String username);

	/**
	 * Method to get all users
	 * 
	 * @return list of {@link User}
	 */
	List<User> findAllUsers();
}

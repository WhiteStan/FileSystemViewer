package com.epam.dao;

import java.util.List;

import com.epam.model.users.UserProfile;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Interface specifies methods for work with {@link UserProfile} table
 */
public interface UserProfileDao {

	/**
	 * Method to retrieve all possible roles
	 * 
	 * @return list of {@link UserProfile}
	 */
	List<UserProfile> findAll();
}

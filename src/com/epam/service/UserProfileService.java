package com.epam.service;

import java.util.List;

import com.epam.model.users.UserProfile;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Defines methods for processing requests associated with {@link UserProfile}
 */
public interface UserProfileService {
	/**
	 * Method to retrieve all possible roles
	 * 
	 * @param type uniquely specifies role
	 * @return list of instances {@link UserProfile}
	 */
	List<UserProfile> findAll();
}

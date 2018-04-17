package com.epam.model.users;

import java.io.Serializable;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Enumeration containing information about possible user roles
 */
public enum UserProfileType implements Serializable {

	USER("USER"), ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}
}

package com.epam.test.util;

import java.util.HashSet;
import java.util.Set;

import com.epam.model.users.User;
import com.epam.model.users.UserProfile;

public class UserBuilder {

	public static User buildUser() {
		
		User user = new User();
		
		user.setId(1);
		user.setPassword("pass");
		user.setUsername("someUser");
		
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		
		UserProfile userProfile = new UserProfile();
		
		userProfile.setId(2);
		userProfile.setType("USER");
		userProfiles.add(userProfile);
		
		user.setUserProfiles(userProfiles);
		
		return user;
	}
	
}

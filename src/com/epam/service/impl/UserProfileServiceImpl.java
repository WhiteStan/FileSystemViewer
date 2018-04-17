package com.epam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dao.UserProfileDao;
import com.epam.model.users.UserProfile;
import com.epam.service.UserProfileService;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	public List<UserProfile> findAll() {
		return dao.findAll();
	}
}

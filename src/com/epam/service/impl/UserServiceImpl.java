package com.epam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dao.UserDao;
import com.epam.model.users.User;
import com.epam.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder encoder;

	public User findById(Integer id) {
		
		User user = userDao.findById(id);
		return user;
		
	}

	public User findByUsername(String username) {
		
		User user = userDao.findByUsername(username);
		return user;
		
	}

	public void saveUser(User user) {
		
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.save(user);
		
	}

	public void updateUser(User user) {
		
		User entity = userDao.findById(user.getId());
		
		if (entity != null) {
			
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(encoder.encode(user.getPassword()));
			}
			
			entity.setUsername(user.getUsername());
			entity.setUserProfiles(user.getUserProfiles());
		}
		
	}

	public boolean isUsernameUnique(Integer id, String username) {
		
		User user = findByUsername(username);
		return (user == null || ((id != null) && (user.getId() == id)));
		
	}

	public void deleteUserByUsername(String username) {
		
		userDao.deleteByUsername(username);
		
	}

	public List<User> findAllUsers() {
		
		return userDao.findAllUsers();
		
	}
}

package com.epam.service;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.dao.UserDao;
import com.epam.model.users.User;
import com.epam.service.impl.UserServiceImpl;
import com.epam.test.util.UserBuilder;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTest {

	@Mock
	private UserDao userDao;

	@Mock
	private PasswordEncoder encoder;

	@InjectMocks
	private UserServiceImpl userService;

	private final static Integer TEST_ID = 1;
	
	private final static Integer WRONG_TEST_ID = 2;
	
	private final static String USERNAME = "user";
	
	private final static String WRONG_USERNAME = "wrong";
	
	private final static String ENCODED_PASS = "encoded";

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(true);
	}

	@Test
	public void findById_GetUserTest() {
		
		User user = UserBuilder.buildUser();
		
		when(userDao.findById(TEST_ID)).thenReturn(user);
		assertEquals(user, userService.findById(TEST_ID));
		
		verify(userDao).findById(TEST_ID);
		
	}
	
	@Test
	public void findById_GetUserByWrongIdTest() {
		
		User user = UserBuilder.buildUser();
		
		when(userDao.findById(TEST_ID)).thenReturn(user);
		assertNotEquals(user, userService.findById(WRONG_TEST_ID));
		
		verify(userDao).findById(WRONG_TEST_ID);
	}

	@Test
	public void findByUsername_GetUserTest() {
		
		User user = UserBuilder.buildUser();
		
		when(userDao.findByUsername(USERNAME)).thenReturn(user);
		assertEquals(user, userService.findByUsername(USERNAME));
		
		verify(userDao).findByUsername(USERNAME);
	}
	
	@Test
	public void findByUsername_GetUserByWrongUsernameTest() {
		
		User user = UserBuilder.buildUser();
		
		when(userDao.findByUsername(USERNAME)).thenReturn(user);
		assertNotEquals(user, userService.findByUsername(WRONG_USERNAME));
		
		verify(userDao).findByUsername(WRONG_USERNAME);
	}

	@Test
	public void saveUser_SuccessfulSaveTest() {
		
		User user = UserBuilder.buildUser();
		
		when(encoder.encode(user.getPassword())).thenReturn(ENCODED_PASS);
		userService.saveUser(user);
		
		verify(userDao).save(user);
	}
	
	@Test
	public void updateUser_SuccessfulUpdateTest() {
		
		User user = UserBuilder.buildUser();
		
		when(userDao.findById(TEST_ID)).thenReturn(user);
		userService.updateUser(user);
		
		verify(userDao).findById(TEST_ID);
	}
	
	@Test
	public void deleteUser_SuccessfulDeleteTest() {
		
		userService.deleteUserByUsername(USERNAME);
		verify(userDao).deleteByUsername(USERNAME);
	}


}

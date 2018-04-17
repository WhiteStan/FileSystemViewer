package com.epam.controller;

import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.epam.model.users.User;
import com.epam.test.util.CommonDatabaseOperations;
import com.epam.test.util.UserBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class UserRestControllerIntegrationTest {

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		
		Operation operation = sequenceOf(CommonDatabaseOperations.DELETE_ALL, CommonDatabaseOperations.INSERT_DATA);
		
		DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
		dbSetup.launch();
	}
	
	@Test
	public void saveUser_SuccessfulTest() throws Exception {
		
		User user = UserBuilder.buildUser();
		user.setUsername("anotherUser");
		user.setId(null);
		
		mockMvc.perform(post("/user").contentType(contentType).content(getJsonObject(user)))
				.andExpect(status().isOk());
	}

	@Test
	public void updateUser_SuccessfulTest() throws Exception {
		
		User user = UserBuilder.buildUser();
		user.setUsername("newUser");
		
		mockMvc.perform(put("/user").contentType(contentType).content(getJsonObject(user)))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteUser_SuccessfulDelete() throws Exception {
		
		User user = UserBuilder.buildUser();
		String username = user.getUsername();
		
		mockMvc.perform(delete("/user?username="+username)).andExpect(status().isOk());
	}

	private String getJsonObject(Object obj) throws JsonProcessingException {
		
		return mapper.writeValueAsString(obj);
	}
}

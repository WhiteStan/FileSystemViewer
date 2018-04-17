package com.epam.controller;

import org.junit.After;
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

import com.epam.util.FileSystemUtil;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
@WebAppConfiguration
public class FilesystemRestControllerIntegrationTest {

	private MockMvc mockMvc;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static final String PATH = "testing";

	private static final String CREATE_FOLDER_PATH = "testing/Test";

	private static final String CREATE_FILE_PATH = "testing/Test.txt";

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() throws Exception {

		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		FileSystemUtil.createFolder(PATH);
	}

	@After
	public void tearDown() {

		FileSystemUtil.deleteEntity(CREATE_FILE_PATH);
		FileSystemUtil.deleteEntity(CREATE_FOLDER_PATH);
		FileSystemUtil.deleteEntity(PATH);

	}

	@Test
	public void getFileSystem_GetEntitiesTest() throws Exception {
		mockMvc.perform(get("/filesystem?path=" + PATH)).andExpect(status().isOk());
	}

	@Test
	public void postFolder_SuccessfulCreationTest() throws Exception {

		mockMvc.perform(post("/saveFolder").contentType(contentType).content(CREATE_FOLDER_PATH))
				.andExpect(status().isOk());

	}

	@Test
	public void postFile_SuccessfulCreationTest() throws Exception {

		mockMvc.perform(post("/saveFile").contentType(contentType).content(CREATE_FILE_PATH))
				.andExpect(status().isOk());

	}

	@Test
	public void deleteEntity_SuccessfulFileDelettionTest() throws Exception {
		FileSystemUtil.createFile(CREATE_FILE_PATH);

		mockMvc.perform(delete("/filesystem?path=" + CREATE_FILE_PATH)).andExpect(status().isOk());

	}

}

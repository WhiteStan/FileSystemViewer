package com.epam.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.epam.service.FileSystemServiceIntegrationTest;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
public class FileSystemUtilIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemServiceIntegrationTest.class);

	private static final String PATH = "testing";

	private static final String CREATE_FOLDER_PATH = "testing/Test";

	private static final String CREATE_FOLDER_WRONG_PATH = "wrong/Test";

	private static final String CREATE_FILE_PATH = "testing/Test.txt";

	private static final String CREATE_FILE_WRONG_PATH = "wrong/Test.txt";

	@Before
	public void setUp() throws Exception {

		FileSystemUtil.createFolder(PATH);
	}

	@After
	public void tearDown() {

		FileSystemUtil.deleteEntity(CREATE_FILE_PATH);
		FileSystemUtil.deleteEntity(CREATE_FOLDER_PATH);
		FileSystemUtil.deleteEntity(PATH);
	}
	
	@Test
	public void createFolder_SuccessfulCreationTest() {

		boolean result = FileSystemUtil.createFolder(CREATE_FOLDER_PATH);
		assertTrue(result);

	}

	@Test
	public void createFolder_CreationWithWrongPathTest() {

		boolean result = FileSystemUtil.createFolder(CREATE_FOLDER_WRONG_PATH);
		assertFalse(result);
	}

	@Test
	public void createFile_SuccessfulCreationTest() throws Exception {

		boolean result = FileSystemUtil.createFile(CREATE_FILE_PATH);
		assertTrue(result);

	}

	@Test(expected = IOException.class)
	public void createFile_CreationWithWrongPathTest() throws Exception {
		FileSystemUtil.createFile(CREATE_FILE_WRONG_PATH);
	}

	@Test
	public void deleteEntity_SuccessfulFileDeletionTest() throws Exception {

		try {
			FileSystemUtil.createFile(CREATE_FILE_PATH);
			boolean result = FileSystemUtil.deleteEntity(CREATE_FILE_PATH);
			assertTrue(result);

		} catch (IOException e) {

			LOGGER.error("File deletion error");

		} 
	}
}

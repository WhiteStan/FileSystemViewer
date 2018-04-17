package com.epam.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.epam.util.FileSystemUtil;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:mvc-dispatcher-servlet.xml" })
public class FileSystemServiceIntegrationTest {

	@Autowired
	private FileSystemService fileSystemService;

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemServiceIntegrationTest.class);

	private static final String PATH = "testing";

	private static final String WRONG_PATH = "wrong";

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
	public void getFileSystem_GetEntitiesTest() {

		File dir = new File(PATH);
		Set<String> expected = new HashSet<String>(Arrays.asList(dir.list()));
		Set<String> result = fileSystemService.getFileSystem(PATH).getSubEntities().keySet();
		assertEquals(expected, result);

	}

	@Test(expected = InvalidFilesystemPathException.class)
	public void getFileSystem_GetEntitiesWithWrongPathTest() {

		fileSystemService.getFileSystem(WRONG_PATH).getSubEntities();

	}

	@Test
	public void createFolder_SuccessfulCreationTest() {

		boolean result = fileSystemService.createFolder(CREATE_FOLDER_PATH);
		assertTrue(result);

	}

	@Test(expected = InvalidFilesystemPathException.class)
	public void createFolder_CreationWithWrongPathTest() {

		fileSystemService.createFolder(CREATE_FOLDER_WRONG_PATH);

	}

	@Test
	public void createFile_SuccessfulCreationTest() {

		boolean result = fileSystemService.createFile(CREATE_FILE_PATH);
		assertTrue(result);

	}

	@Test(expected = RuntimeException.class)
	public void createFile_CreationWithWrongPathTest() {
		fileSystemService.createFile(CREATE_FILE_WRONG_PATH);
	}

	@Test
	public void deleteEntity_SuccessfulFileDeletionTest() throws Exception {

		try {
			FileSystemUtil.createFile(CREATE_FILE_PATH);
			boolean result = fileSystemService.deleteEntity(CREATE_FILE_PATH);
			assertTrue(result);

		} catch (IOException e) {

			LOGGER.error("File deletion error");

		} 
	}

	@Test(expected = InvalidFilesystemPathException.class)
	public void deleteEntity_DeletionWithWrongPathTest() {
		fileSystemService.deleteEntity(CREATE_FILE_WRONG_PATH);
	}
}

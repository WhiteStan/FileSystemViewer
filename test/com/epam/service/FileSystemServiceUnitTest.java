package com.epam.service;

import java.io.File;
import com.epam.model.filesystem.FileSystemEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.service.impl.FileSystemServiceImpl;
import com.epam.test.util.FileSystemBuilder;
import com.epam.util.FileSystemUtil;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileSystemServiceImpl.class)
public class FileSystemServiceUnitTest {

	@InjectMocks
	FileSystemServiceImpl service;
	
	
	private static final String PATH = "testing";
	private static final String CREATE_FOLDER_PATH = "testing/Test";
	private static final String CREATE_FILE_PATH = "testing/Test.txt";
	
	private File file = mock(File.class);
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(true);
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
		
		when(file.listFiles()).thenReturn(new File[] {});
        whenNew(File.class).withArguments(PATH).thenReturn(file);
        
		FileSystemEntity expected = FileSystemBuilder.build();
		FileSystemEntity actual = service.getFileSystem(PATH);
		
		assertEquals(expected, actual);
		
		verify(file).listFiles();
	}
	
	@Test
	public void createFolder_SuccessfulCreationTest() throws Exception {
		
		when(file.getParent()).thenReturn(PATH);
		when(file.mkdir()).thenReturn(true);
		
		File parentFile = mock(File.class);
		when(parentFile.isDirectory()).thenReturn(true);
		
        whenNew(File.class).withArguments(CREATE_FOLDER_PATH).thenReturn(file);
        whenNew(File.class).withArguments(PATH).thenReturn(parentFile);
        
		boolean result = service.createFolder(CREATE_FOLDER_PATH);
		
		assertTrue(result);
		
		verifyStatic();
		FileSystemUtil.createFolder(CREATE_FOLDER_PATH);
	}
	
	@Test
	public void createFile_SuccessfulCreationTest() throws Exception {
		
		when(file.getParent()).thenReturn(PATH);
		when(file.createNewFile()).thenReturn(true);
		
		File parentFile = mock(File.class);
		when(parentFile.isDirectory()).thenReturn(true);
		
        whenNew(File.class).withArguments(CREATE_FILE_PATH).thenReturn(file);
        whenNew(File.class).withArguments(PATH).thenReturn(parentFile);
        
		boolean result = service.createFile(CREATE_FILE_PATH);
		
		assertTrue(result);

		verifyStatic();
		FileSystemUtil.createFile(CREATE_FILE_PATH);
	}
}

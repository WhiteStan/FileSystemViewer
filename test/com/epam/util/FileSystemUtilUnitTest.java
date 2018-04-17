package com.epam.util;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.util.FileSystemUtil;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest(FileSystemUtil.class)
public class FileSystemUtilUnitTest {

	@InjectMocks
	private FileSystemUtil util;
	
	
	private static final String PATH = "testing";
	private static final String CREATE_FOLDER_PATH = "testing/Test";
	private static final String CREATE_FILE_PATH = "testing/Test.txt";
	
	private File file = mock(File.class);
	
	@BeforeClass
	public static void setUp() throws Exception {

		MockitoAnnotations.initMocks(true);
		
		FileSystemUtil.createFolder(PATH);
	}

	@AfterClass
	public static void tearDown() {
		
		FileSystemUtil.deleteEntity(PATH);
	}
	@Test
	public void createFolder_SuccessfulCreationTest() throws Exception {
		
		when(file.mkdir()).thenReturn(true);
		
        whenNew(File.class).withArguments(CREATE_FOLDER_PATH).thenReturn(file);
        
		boolean result = FileSystemUtil.createFolder(CREATE_FOLDER_PATH);
		
		assertTrue(result);
		
		verify(file).mkdir();
	}
	
	@Test
	public void createFile_SuccessfulCreationTest() throws Exception {
		
		when(file.createNewFile()).thenReturn(true);
		
        whenNew(File.class).withArguments(CREATE_FILE_PATH).thenReturn(file);
        
		boolean result = FileSystemUtil.createFile(CREATE_FILE_PATH);
		
		assertTrue(result);
		
		verify(file).createNewFile();
	}
	
	@Test
	public void deleteEntity_SuccessfulFileDeletionTest() throws Exception {
		
		when(file.exists()).thenReturn(true);
		when(file.delete()).thenReturn(true);
		
        whenNew(File.class).withArguments(CREATE_FILE_PATH).thenReturn(file);
        
		boolean result = FileSystemUtil.deleteEntity(CREATE_FILE_PATH);
		
		assertTrue(result);
		
		verify(file).delete();
	}
}

package com.epam.service;

import com.epam.model.filesystem.FileSystemEntity;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Defines methods for processing requests associated with {@link FileSystemEntity}
 */
public interface FileSystemService {

	/**
	 * Method to retrieve information about file system structure at specified path
	 * 
	 * @param path points to certain place in file system
	 * @return instance of {@link FileSystemEntity}
	 * @throws InvalidFilesystemPathException if path is invalid;
	 */
	FileSystemEntity getFileSystem(String path);

	/**
	 * Method to create folder at specified path
	 * 
	 * @param path points to certain place in file system
	 * @return result of the operation
	 * @throws InvalidFilesystemPathException if path is invalid;
	 */
	boolean createFolder(String path);


	/**
	 * Method to create file at specified path
	 * 
	 * @param path points to certain place in file system
	 * @return result of the operation
	 * @throws InvalidFilesystemPathException if path is invalid;
	 * @throws RuntimeException If an I/O error occurred or access to file is denied;
	 */
	boolean createFile(String path);


	/**
	 * Method to delete file or folder at specified path
	 * 
	 * @param path points to certain place in file system
	 * @return result of the operation
	 * @throws InvalidFilesystemPathException if path is invalid;
	 */
	boolean deleteEntity(String path);
}

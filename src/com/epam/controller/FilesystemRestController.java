package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.model.filesystem.FileSystemEntity;
import com.epam.service.FileSystemService;

/**
 * Defines methods which processing REST requests
 */
@RestController
public class FilesystemRestController {

	@Autowired
	private FileSystemService fileSystemService;

	/**
	 * Endpoint that retrieve information about files and folder contained at
	 * specified path
	 * 
	 * @param path
	 *            that points place in filesystem for analysis
	 * @return FileSystemEntity - class containing data about structure of
	 *         filesystem at the path
	 */
	@RequestMapping(value = "/filesystem", method = RequestMethod.GET)
	public FileSystemEntity fileSystem(@RequestParam("path") String path) {
		
		FileSystemEntity fileSystemEntity = fileSystemService.getFileSystem(path);
		return fileSystemEntity;
		
	}

	/**
	 * Endpoint to create new folder
	 * 
	 * @param path
	 *            specifies place in the filesystem to create new folder
	 * @return status code, code 400 points that error occurred during creation
	 */
	@RequestMapping(value = "/filesystem", method = RequestMethod.POST)
	public ResponseEntity<?> saveFolder(@RequestBody String path) {
		
		return fileSystemService.createFolder(path) ? ResponseEntity.status(HttpStatus.OK).body(null)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Folder already exists\"}");
	}

	/**
	 * Endpoint to create new file
	 * 
	 * @param path
	 *            specifies place in the filesystem to create new file
	 * @return status code, code 400 points that error occurred during the
	 *         process of creation
	 */
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseEntity<?> saveFile(@RequestBody String path) {
		
		return fileSystemService.createFile(path) ? ResponseEntity.status(HttpStatus.OK).body(null)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"File already exists\"}");
	}

	/**
	 * Endpoint to delete file or folder
	 * 
	 * @param path
	 *            to the entity to delete
	 * @return status code, code 400 point that error occurred
	 */
	@RequestMapping(value = "/filesystem", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEntity(@RequestParam("path") String path) {
		
		return fileSystemService.deleteEntity(path) ? ResponseEntity.status(HttpStatus.OK).body(null)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Deletion error\"}");
	}
}

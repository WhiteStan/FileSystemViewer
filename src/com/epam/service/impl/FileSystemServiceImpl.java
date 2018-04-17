package com.epam.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epam.model.filesystem.FileSystemEntity;
import com.epam.model.filesystem.FileSystemType;
import com.epam.service.FileSystemService;
import com.epam.service.InvalidFilesystemPathException;
import com.epam.util.FileSystemUtil;

@Service
public class FileSystemServiceImpl implements FileSystemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSystemServiceImpl.class);

	@Override
	public FileSystemEntity getFileSystem(String path) {

		FileSystemEntity fileSystemEntity = new FileSystemEntity();
		fileSystemEntity.setName(path);
		fileSystemEntity.setType(FileSystemType.FOLDER);

		File[] files;
		File dir = new File(path);
		files = dir.listFiles();

		if (files != null) {

			Map<String, FileSystemType> subEntities = new HashMap<String, FileSystemType>();

			for (File file : files) {
				subEntities.put(file.getName(), file.isDirectory() ? FileSystemType.FOLDER : FileSystemType.FILE);
			}

			fileSystemEntity.setSubEntities(subEntities);
		} else {
			LOGGER.error("Error at getFileSystem(): Invalid path");
			throw new InvalidFilesystemPathException();
		}

		return fileSystemEntity;
	}

	@Override
	public boolean createFolder(String path) {

		boolean result;

		File dir = new File(path);
		if (validatePath(dir.getParent())) {
			result = FileSystemUtil.createFolder(path);
		} else {
			LOGGER.error("Error at createFolder(): Invalid path");
			throw new InvalidFilesystemPathException();
		}

		return result;
	}

	@Override
	public boolean createFile(String path) {

		boolean result;

		File dir = new File(path);

		if (validatePath(dir.getParent())) {

			try {
				result = FileSystemUtil.createFile(path);
			} catch (Exception e) {
				LOGGER.error("File creation failed");
				throw new RuntimeException(e);
			}
		} else {
			LOGGER.error("Error at createFile(): Invalid path");
			throw new InvalidFilesystemPathException();
		}
		return result;
	}

	@Override
	public boolean deleteEntity(String path) {

		boolean result;

		File entity = new File(path);

		if (entity.exists()) {
			result = FileSystemUtil.deleteEntity(path);
		} else {
			LOGGER.error("Error at deleteEntity(): Invalid path");
			throw new InvalidFilesystemPathException();
		}

		return result;
	}
	
	private boolean validatePath(String path) {
		File dir = new File(path);
		return dir.isDirectory();
	}
}

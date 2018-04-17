package com.epam.test.util;

import java.util.HashMap;

import com.epam.model.filesystem.FileSystemEntity;
import com.epam.model.filesystem.FileSystemType;

public class FileSystemBuilder {

	public static FileSystemEntity build(){
		FileSystemEntity entity = new FileSystemEntity();
		entity.setName("testing");
		entity.setType(FileSystemType.FOLDER);
		entity.setSubEntities(new HashMap<String, FileSystemType>());
		return entity;
	}
}

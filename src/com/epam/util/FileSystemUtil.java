package com.epam.util;

import java.io.File;

public class FileSystemUtil {

	public static boolean createFile(String path) throws Exception {
		boolean result;
		File file = new File(path);

		result = file.createNewFile();
		return result;
	}

	public static boolean deleteEntity(String path) {
		boolean result = false;
		File file = new File(path);
		if (file.exists()) {
			result = file.delete();
		}
		return result;
	}

	public static boolean createFolder(String path)  {
		
		boolean result;
		File dir = new File(path);

		result = dir.mkdir();
		return result;
	}
}

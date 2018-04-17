package com.epam.model.filesystem;

import java.util.Map;

/**
 * 
 * @author Stanislau_Makouski
 *
 * Entity containing information about file system structure
 */
public class FileSystemEntity {

	private String name;
	private FileSystemType type;
	private Map<String, FileSystemType> subEntities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FileSystemType getType() {
		return type;
	}

	public void setType(FileSystemType type) {
		this.type = type;
	}

	public Map<String, FileSystemType> getSubEntities() {
		return subEntities;
	}

	public void setSubEntities(Map<String, FileSystemType> subEntities) {
		this.subEntities = subEntities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subEntities == null) ? 0 : subEntities.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileSystemEntity other = (FileSystemEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subEntities == null) {
			if (other.subEntities != null)
				return false;
		} else if (!subEntities.equals(other.subEntities))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FileSystemEntity [name=" + name + ", type=" + type + ", subEntities=" + subEntities + "]";
	}

}

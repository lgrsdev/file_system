package com.lgrsdev.filesystem;

import java.util.Date;

public abstract class FsEntity {

	private static final int NAME_MAX_CHAR = 32;

	private String name;
	private Date date;
	private Directory parentDir;

	public FsEntity(String name) {
		super();
		this.name = requireMaxChar(name);
		this.date = new Date();
	}

	public String getName() {
		return name;
	}
	
	public void setParentDir(Directory parentDir) {
		this.parentDir = parentDir;
	}

	public Directory getParentDir() {
		return parentDir;
	}
	
	public boolean isDirectory() {
		return this instanceof Directory;
	}

	private static String requireMaxChar(String name) {
		if (null == name || name.isEmpty() || name.length() > NAME_MAX_CHAR)
			throw new IllegalArgumentException(
					"fsEntity name is mandatory and should be up to " + NAME_MAX_CHAR + " chars");
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FsEntity other = (FsEntity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "name=" + name + ", date=" + date;
	}

}

package com.lgrsdev.filesystem;

import java.util.HashSet;
import java.util.Set;

public class Directory extends FsEntity {

	private Set<FsEntity> children;

	public Directory(String name) {
		super(name);
		children = new HashSet<>();
	}

	public void addChild(FsEntity child) {
		child.setParentDir(this);
		children.add(child);
	}

	public void removeChild(FsEntity child) {
		children.remove(child);
	}

	public Set<FsEntity> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return "Directory [" + super.toString() + "]";
	}

}

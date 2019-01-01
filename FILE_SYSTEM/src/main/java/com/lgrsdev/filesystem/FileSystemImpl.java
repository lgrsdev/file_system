package com.lgrsdev.filesystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileSystemImpl implements FileSystem {

	private Map<String, FsEntity> cache;

	public FileSystemImpl() {
		super();
		this.cache = new HashMap<>();
		this.cache.put("/", new Directory("/"));
	}

	@Override
	public void addFile(String parentDirName, String fileName, long fileSize) {
		addChild(parentDirName, new File(fileName, fileSize));
	}

	@Override
	public void addDir(String parentDirName, String dirName) {
		addChild(parentDirName, new Directory(dirName));
	}

	@Override
	public void delete(String name) {
		if (name.equals("/"))
			throw new UnsupportedOperationException("cannot delete root");
		FsEntity fsEntity = cache.get(name);
		if (null == fsEntity)
			throw new IllegalArgumentException("no such file or directory \"" + name + "\"");
		deleteRecursively(fsEntity); // preventing memory leak through cache
	}

	@Override
	public void showFileSystem() {
		printRecursively(getRoot(), "");
	}

	private void addChild(String parentDirName, FsEntity child) {
		if (isExists(child.getName()))
			throw new IllegalArgumentException("\"" + child.getName() + "\" allready exists");
		FsEntity parent = cache.get(parentDirName);
		if (null == parent || !parent.isDirectory())
			throw new IllegalArgumentException("\"" + parentDirName + "\" is not a directory");
		((Directory) parent).addChild(child);
		cache.put(child.getName(), child);
	}

	private boolean isExists(String fsEntityName) {
		return cache.containsKey(fsEntityName);
	}

	private void deleteRecursively(FsEntity fsEntity) {
		if (fsEntity.isDirectory()) {
			Set<FsEntity> children = new HashSet<>(((Directory) fsEntity).getChildren());
			children.stream().forEach(this::deleteRecursively);
		}
		fsEntity.getParentDir().removeChild(fsEntity);
		cache.remove(fsEntity.getName());
	}

	private FsEntity getRoot() {
		return cache.get("/");
	}

	private void printRecursively(FsEntity start, String indent) {
		System.out.println(indent + start);
		if (start.isDirectory())
			 ((Directory) start).getChildren().stream().forEach(child -> printRecursively(child, indent + "	"));
	}

}

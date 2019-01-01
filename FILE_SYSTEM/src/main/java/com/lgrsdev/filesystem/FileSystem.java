package com.lgrsdev.filesystem;

public interface FileSystem {

	void addFile (String parentDirName, String fileName, long fileSize);

	void addDir (String parentDirName, String dirName);

	void delete (String name);

	void showFileSystem ();

}

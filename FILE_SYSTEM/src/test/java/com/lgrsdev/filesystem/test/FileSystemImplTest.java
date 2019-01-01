package com.lgrsdev.filesystem.test;

import org.junit.Before;
import org.junit.Test;

import com.lgrsdev.filesystem.FileSystem;
import com.lgrsdev.filesystem.FileSystemImpl;

public class FileSystemImplTest {
	
	private FileSystem fs;
	
    @Before
    public void init() {
        fs = new FileSystemImpl();
    }
	
	@Test
	public void shouldAddFile() {
		fs.addFile("/", "f1", 46);
        fs.showFileSystem();
	}
	
	@Test
	public void shouldAddDir() {
		fs.addDir("/", "d1");
        fs.showFileSystem();
	}
	
	@Test
	public void shouldDeleteFile() {
		fs.addFile("/", "f1", 46);
        fs.showFileSystem();
        System.out.println();
        System.out.println("deleting...");
        System.out.println();
        fs.delete("f1");
        fs.showFileSystem();
	}
	
	@Test
	public void shouldDeleteDir() {
		fs.addDir("/", "d1");
        fs.showFileSystem();
        System.out.println();
        System.out.println("deleting...");
        System.out.println();
        fs.delete("d1");
        fs.showFileSystem();
	}
	
	@Test
	public void shouldShowFs() {
		fs.addDir("/", "d1");
		fs.addFile("d1", "f1", 50);
		fs.addDir("d1", "d2");
		fs.addFile("d2", "f2", 80);
		fs.addDir("d2", "d3");
		fs.addDir("d2", "d4");
		fs.addDir("d2", "d5");
		fs.showFileSystem();
        System.out.println();
        System.out.println("deleting d3...");
        System.out.println();
        fs.delete("d3");
		fs.showFileSystem();
        System.out.println();
        System.out.println("deleting f2...");
        System.out.println();
        fs.delete("f2");
        System.out.println();
        fs.showFileSystem();
        System.out.println("deleting d2...");
        System.out.println();
        fs.delete("d2");
        System.out.println();
        fs.showFileSystem();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void shouldNotDeleteRoot() {
		fs.delete("/");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddExistingFile() {
		fs.addFile("/", "f1", 46);
		fs.addFile("/", "f1", 27);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddExistingDirectory() {
		fs.addDir("/", "d1");
		fs.addDir("/", "d1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddFileToNonExistingDir() {
		fs.addFile("foo", "f1", 46);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddFileWithNegativeSize() {
		fs.addFile("/", "f1", -3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddFileWithZeroSize() {
		fs.addFile("/", "f1", 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddFileExceeding32Chars() {
		fs.addFile("/", "111111111111111111111111111111111", 46);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddDirExceeding32Chars() {
		fs.addDir("/", "111111111111111111111111111111111");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddDirWithEmptyName() {
		fs.addDir("/", "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAddFileWithNameNull() {
		fs.addFile("/", null, 46);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotDeleteNonExistingFsEntity() {
		fs.delete("foo");
	}
	
}

package com.lgrsdev.filesystem;

public class File extends FsEntity {

	private long size;

	public File(String name, long size) {
		super(name);
		this.size = requirePositiveLong(size);
	}

	private static long requirePositiveLong(long size) {
		if (size <= 0)
			throw new IllegalArgumentException("file size is mandatory and should be positive");
		return size;
	}

	@Override
	public String toString() {
		return "File [" + super.toString() + ", size=" + size + "]";
	}

}

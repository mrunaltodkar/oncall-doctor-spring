package com.couponsdeal.gridfs.couponsdealgridfs.entity;

public class FileResource {

	private String id;

	public FileResource() {
		super();
	}

	public FileResource(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "FileResource [id=" + id + "]";
	}

}

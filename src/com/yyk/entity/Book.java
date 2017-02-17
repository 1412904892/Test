package com.yyk.entity;

public class Book {
	private int id;
	private String bname; //书名
	private int bcount;   //借的次数
	private int status;   //是否已经借出
	public Book() {
		super();
	}
	
	public Book(int id, String bname, int bcount, int status) {
		super();
		this.id = id;
		this.bname = bname;
		this.bcount = bcount;
		this.status = status;
	}

	public Book(String bname, int bcount, int status) {
		super();
		this.bname = bname;
		this.bcount = bcount;
		this.status = status;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}

package com.yyk.entity;

public class Record {
	private int id;
	private int uid;
	private int bid;
	private String lendtime;
	private String returntime;
	
	public Record() {
		super();
	}

	public Record(int id, int uid, int bid, String lendtime, String returntime) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}

	public Record(int uid, int bid, String lendtime, String returntime) {
		super();
		this.uid = uid;
		this.bid = bid;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getLendtime() {
		return lendtime;
	}

	public void setLendtime(String lendtime) {
		this.lendtime = lendtime;
	}

	public String getReturntime() {
		return returntime;
	}

	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}

	
}

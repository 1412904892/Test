package com.yyk.entity;

public class Record2 {
	private int id;
	private int bid;
	private String uname;
    private String bname;
    private String lendtime;
    private String returntime;
    
    
    
    
	public Record2() {
		super();
	}
	
	
	public Record2(String uname, String bname, String lendtime, String returntime) {
		super();
		this.uname = uname;
		this.bname = bname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}


	public Record2(int bid, String uname, String bname, String lendtime, String returntime) {
		super();
		this.bid = bid;
		this.uname = uname;
		this.bname = bname;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
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

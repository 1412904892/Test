package com.yyk.Dao;

import java.util.List;

import com.yyk.entity.Record;
import com.yyk.entity.Record2;

public interface RecordDao {
	public Record queryRecordById(int rid);
	public boolean saveRecord(Record record);
	public boolean updateRecord(Record record);
	
	public List<Record2> queryAllRecords(); 
	public List<Record2> queryRecordByUname(String uname);
	public List<Record2> queryRecordByBname(String bname);
	public List<Record2> queryUserRecordByreturn(boolean flag,String uname);
}

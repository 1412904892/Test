package com.yyk.biz.Impl;

import java.util.List;

import com.yyk.Dao.RecordDao;
import com.yyk.Dao.Impl.RecordDaoImpl;
import com.yyk.biz.RecordBiz;
import com.yyk.entity.Record2;

public class RecordBizimpl implements RecordBiz {
 private RecordDao recorddao=null;
 public RecordBizimpl(){
	 recorddao=new RecordDaoImpl();
 }
	@Override
	public List<Record2> queryUserRecord(String uname) {
		
		return recorddao.queryRecordByUname(uname);
	}

	@Override
	public List<Record2> queryBookRecord(String bname) {

		return recorddao.queryRecordByBname(bname);
	}

	@Override
	public List<Record2> queryHasReturnReocrd(String uname) {

		return recorddao.queryUserRecordByreturn(true, uname);
	}

	@Override
	public List<Record2> queryNotReturnRecord(String uname) {

		return recorddao.queryUserRecordByreturn(false, uname);
	}

	@Override
	public List<Record2> queryAllReocrds() {

		return recorddao.queryAllRecords();
	}

}

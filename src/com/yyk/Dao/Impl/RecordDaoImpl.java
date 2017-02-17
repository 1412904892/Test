package com.yyk.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import com.yyk.Dao.RecordDao;
import com.yyk.entity.Record;
import com.yyk.entity.Record2;

public class RecordDaoImpl extends BaseDao implements RecordDao {

	@Override
	public Record queryRecordById(int rid) {
		String sql="select id,uid,bid,lendtime,returntime from records where id=?";
		List<Object> params=new ArrayList<Object>();
		List<Record> rList=new ArrayList<Record>();
		
		params.add(rid);
		try {
			rList=this.operquery(sql, params, Record.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rList.size()>0) return rList.get(0);
		else return null;
	}

	@Override
	public boolean saveRecord(Record record) {
		String sql="insert into records(uid,bid,lendtime,returntime) values(?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		
		params.add(record.getUid());
		params.add(record.getBid());
		params.add(record.getLendtime());
		params.add(record.getReturntime());
		
		return this.operUpdate(sql, params);
	}

	@Override
	public boolean updateRecord(Record record) {
		String sql="update records set uid=?,bid=?,lendtime=?,returntime=? where id=?";
		List<Object> params=new ArrayList<Object>();
		
		params.add(record.getUid());
		params.add(record.getBid());
		params.add(record.getLendtime());
		params.add(record.getReturntime());
		params.add(record.getId());
	
		return this.operUpdate(sql, params);
	}

	@Override
	public List<Record2> queryAllRecords() {
		String sql="select r.id,b.id as bid, u.uname,b.bname,r.lendtime,r.returntime from "
				+ " users u,books b,records r where r.uid=u.id and r.bid=b.id";
		List<Record2> rList=null;
		try {
			rList=this.operquery(sql, null, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record2> queryRecordByUname(String uname) {
		String sql="select r.id,b.id as bid,u.uname,b.bname,r.lendtime,r.returntime from "
				+" users u,books b,records r where r.uid=u.id and r.bid=b.id and uname=?";
		List<Object> params=new ArrayList<Object>();
		List<Record2> rList=null;
		params.add(uname);
		try {
			rList=this.operquery(sql, params, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record2> queryRecordByBname(String bname) {
		String sql="select r.id,b.id as bid,u.uname,b.bname,r.lendtime,r.returntime from "
				+" users u,books b,records r where r.uid=u.id and r.bid=b.id and bname=?";
		List<Object> params=new ArrayList<Object>();
		List<Record2> rList=null;
		params.add(bname);
		try {
			rList=this.operquery(sql, params, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

	@Override
	public List<Record2> queryUserRecordByreturn(boolean flag, String uname) {
		String sql=null;
		List<Record2> rList=null;
		List<Object> params=new ArrayList<Object>();
		if (flag){ //“‘ªπ
			sql="select r.id,b.id as bid,u.uname,b.bname,r.lendtime,r.returntime from "
					+" users u,books b,records r where r.uid=u.id and r.bid=b.id and "
					+ " returntime is not null and uname=?";
		}else{   //Œ¥ªπ
			sql="select r.id,b.id as bid,u.uname,b.bname,r.lendtime,r.returntime from "
					+" users u,books b,records r where r.uid=u.id and r.bid=b.id and "
					+ " returntime is null and uname=?";
		}
		params.add(uname);
		try {
			rList=this.operquery(sql, params, Record2.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rList;
	}

}

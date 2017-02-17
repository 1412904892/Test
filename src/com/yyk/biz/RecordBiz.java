package com.yyk.biz;

import java.util.List;

import com.yyk.entity.Record2;

public interface RecordBiz {
	public List<Record2> queryUserRecord(String uname); //查看指定用户的信息
	public List<Record2> queryBookRecord(String bname); //查看指定名字的书籍信息
	public List<Record2> queryHasReturnReocrd(String uname); //查询已还书籍
	public List<Record2> queryNotReturnRecord(String  uname); //查询未还的书籍
	public List<Record2> queryAllReocrds();  //查询所有的记录
}

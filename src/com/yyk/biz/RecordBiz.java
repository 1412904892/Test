package com.yyk.biz;

import java.util.List;

import com.yyk.entity.Record2;

public interface RecordBiz {
	public List<Record2> queryUserRecord(String uname); //�鿴ָ���û�����Ϣ
	public List<Record2> queryBookRecord(String bname); //�鿴ָ�����ֵ��鼮��Ϣ
	public List<Record2> queryHasReturnReocrd(String uname); //��ѯ�ѻ��鼮
	public List<Record2> queryNotReturnRecord(String  uname); //��ѯδ�����鼮
	public List<Record2> queryAllReocrds();  //��ѯ���еļ�¼
}

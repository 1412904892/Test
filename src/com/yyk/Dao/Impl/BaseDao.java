package com.yyk.Dao.Impl;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BaseDao {
	private static final String DRIVER="org.sqlite.JDBC";
    private static final String URL="jdbc:sqlite:e:\\date.db";
	
	public Connection getConn(){
		Connection conn=null;
		try {
			Class.forName(DRIVER);  //ע������
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			conn=DriverManager.getConnection(URL);  //�������Ӷ���
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection conn){
		try {
			if (rs!=null){
			rs.close();
			}
			if (pstmt!=null){
				pstmt.close();
			}
			if (conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ���²���
	 */
	public boolean operUpdate(String sql , List<Object> params){
		int res=0;
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=getConn();  //�������ݿ�����
			pstmt=conn.prepareStatement(sql);  //װ��sql��䣬��ִ��
			if (params!=null){
				for (int i=0;i<params.size();i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			res=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return res>0?true:false;
	}
	
	public<T> List<T> operquery(String sql,List<Object> params,Class<T> cls) throws Exception{  //���ͷ���
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement pstmt=null;
		List<T> data=new ArrayList<T>();
		try {
			conn=getConn();  //�������ݿ�����
			pstmt=conn.prepareStatement(sql);  //װ��sql���
			if (params!=null){
				for (int i=0;i<params.size();i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			rs=pstmt.executeQuery();  //��ѯ�����Ľ��
			ResultSetMetaData rsd=rs.getMetaData();// ���Ի�ȡ��Ľṹ�����������е���������
			
			while(rs.next()){
				 T m= cls.newInstance();
				 for (int i=0;i<rsd.getColumnCount();i++){
					 String cloumn_name=rsd.getColumnName(i+1); //�������
					 Object values=rs.getObject(cloumn_name);  //����ж�Ӧ��ֵ
					 Field field=cls.getDeclaredField(cloumn_name);
					 field.setAccessible(true);
					 field.set(m, values);  //�������˽�����Ը�ֵ	
				 }
				 data.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll(rs, pstmt, conn);
		}
		return data;
		
	}	
}

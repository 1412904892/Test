package com.yyk.Dao;

import com.yyk.entity.User;

public interface UserDao {
	public boolean saveUser(User user);     //����һ���û�
	
	public boolean delUser(int id);      //ɾ��һ���û�
	
	public boolean updateUser(User user);   //����
	
	public User queryUser(User user);    //��ѯ
}

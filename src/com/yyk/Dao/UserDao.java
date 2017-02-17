package com.yyk.Dao;

import com.yyk.entity.User;

public interface UserDao {
	public boolean saveUser(User user);     //增加一个用户
	
	public boolean delUser(int id);      //删除一个用户
	
	public boolean updateUser(User user);   //更新
	
	public User queryUser(User user);    //查询
}

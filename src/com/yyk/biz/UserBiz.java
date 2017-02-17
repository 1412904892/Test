package com.yyk.biz;

import com.yyk.entity.User;

public interface UserBiz {
	public User Login(User user);   //用户登录，返回登陆的信息
	public int register(User user);
}

package com.yyk.biz;

import com.yyk.entity.User;

public interface UserBiz {
	public User Login(User user);   //�û���¼�����ص�½����Ϣ
	public int register(User user);
}

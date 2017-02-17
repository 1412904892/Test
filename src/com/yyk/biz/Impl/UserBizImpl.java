package com.yyk.biz.Impl;

import com.yyk.Dao.UserDao;
import com.yyk.Dao.Impl.UserDaoImpl;
import com.yyk.biz.UserBiz;
import com.yyk.entity.User;

public class UserBizImpl implements UserBiz {
  private UserDao userdao=null;
    public  UserBizImpl() {
    	userdao= new UserDaoImpl();
	}
	@Override
	public User Login(User user) {
		return userdao.queryUser(user);
	}

	@Override
	public int register(User user) {
		if (userdao.queryUser(user)!=null){
			return 1;  //用户已经存在
		}else{
			if (userdao.saveUser(user)) return 2;
			else return 3;  //注册失败
		}
	}

}

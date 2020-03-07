package com.yc.C76S3PlySpringBoot.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C76S3PlySpringBoot.dao.UserMapper;
import com.ycC76S3PlySpringBoot.bean.User;

@Service
public class UserBiz {

	@Resource
	private UserMapper um;
	
	public boolean login(String name,String pwd) {
		User user = um.selectByNameAndPassWord(name, pwd);
		return user != null;
	}
}

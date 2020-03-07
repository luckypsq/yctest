package com.yc.C76S3PlySpringBoot.dao;

import com.ycC76S3PlySpringBoot.bean.User;

public interface UserMapper {

	public User selectByNameAndPassWord(String name,String pwd);
}

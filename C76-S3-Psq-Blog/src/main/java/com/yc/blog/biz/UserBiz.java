package com.yc.blog.biz;


import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.yc.blog.bean.User;
import com.yc.blog.bean.UserExample;
import com.yc.blog.dao.UserMapper;
import com.yc.blog.vo.MailService;

@Service
public class UserBiz {
	@Resource
	private UserMapper um;
	
	public void reg(User user,String repwd) throws BaseBizException {
		UserExample ue = new UserExample();
		ue.createCriteria().andAccountEqualTo(user.getAccount());
		
		if(user.getPwd().equals(repwd) == false ) {
			throw new BaseBizException("repwd",101,"两次密码输入不一致");
		}
		if( um.countByExample(ue) > 0) {
			throw new BaseBizException("name",102,"该用户已经被注册");
		}
		um.insert(user);
	}

	public User login(@Valid User user) throws BaseBizException {
		UserExample ue = new UserExample();
		ue.or().andAccountEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andNameEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andPhoneEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());
		ue.or().andEmailEqualTo(user.getAccount()).andPwdEqualTo(user.getPwd());

		List<User> list = um.selectByExample(ue);
		
		if(list.size() == 0) {
			throw new BaseBizException("pwd",103,"用户名或密码错误");
		}else if(list.size() > 1) {
			throw new BaseBizException("name",104,"存在多个相同账号信息");
		}else {
			return list.get(0);
		}
		
		
		
		
	}

	@Resource
	private MailService ms;
	
	public String foget(String account) throws BaseBizException {
		UserExample ue = new UserExample();
		ue.createCriteria().andAccountEqualTo(account);
		List<User> list = um.selectByExample(ue);
		if(list.size() == 1) {
			User user = list.get(0);
			String vcode = System.currentTimeMillis() + "";
			vcode = vcode.substring(vcode.length()-4,vcode.length());
			String content = "您重置密码的验证码是：" +vcode;
			ms.sendSimpleMail(user.getEmail(), "重置密码", content);
			return vcode;
		}else {
			throw new BaseBizException("name",1007,"用户名错误！");
		}
	}
}

package com.yc.C76S3PlySpringBoot;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import com.yc.C76S3PlySpringBoot.biz.UserBiz;
import com.yc.C76S3PlySpringBoot.dao.TagMapper;
import com.yc.C76S3PlySpringBoot.dao.UserMapper;
import com.ycC76S3PlySpringBoot.bean.User;

@SpringBootTest
class C76S3PlySpringBootApplicationTests {
	
	@Resource
	private TagMapper tm;
	@Resource
	private UserBiz ubiz;
	
	@MockBean
	private UserMapper um;

	@Test
	void contextLoads() {
		Assert.isTrue(tm.selectAll().size() > 0, "结果集数量不正确!");
	}

	@Test
	void test1() {
		User user = new User();
		/**
		 * 输入值的设置
		 */
		Mockito.when(um.selectByNameAndPassWord(Mockito.anyString(),Mockito.anyString())).thenReturn(null);
		
		/**
		 * 存根验证
		 */
		Mockito.when(um.selectByNameAndPassWord("yc", "123")).thenReturn(user);
		Mockito.when(um.selectByNameAndPassWord("zhangsan", "123")).thenReturn(null);
		
		boolean bool = ubiz.login("zhangsan", "123");
		Assert.isTrue(bool == false,"登录测试失败");
		
		bool = ubiz.login("yc", "123");
		Assert.isTrue(bool == true,"登录测试失败");
		
		bool = ubiz.login("lisi", "123");
		Assert.isTrue(bool == false,"登录测试失败");
		
		bool = ubiz.login("灵宠", "697");
		Assert.isTrue(bool == false,"登录测试失败");
		
		bool = ubiz.login("林冲", "123");
		Assert.isTrue(bool == false,"登录测试失败");
		
		/**
		 * 行为验证
		 */
		Mockito.verify(um).selectByNameAndPassWord("yc", "123");
		Mockito.verify(um).selectByNameAndPassWord("zhangsan", "123");
		Mockito.verify(um).selectByNameAndPassWord("lisi", "123");
		Mockito.verify(um).selectByNameAndPassWord("灵宠", "697");
		Mockito.verify(um).selectByNameAndPassWord("林冲", "123");
	}
	
}
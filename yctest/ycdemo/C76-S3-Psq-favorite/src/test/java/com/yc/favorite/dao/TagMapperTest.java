package com.yc.favorite.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.yc.favorite.bean.Tag;

public class TagMapperTest {
	
	private SqlSession session;
	@Before
	private void before() throws IOException {
		String resource = "mybatis.xml";
		//读入配置文件
		InputStream in = Resources.getResourceAsStream(resource);
		//创建会话工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		//mybatis的会话底层包装一个jdbc连接
		session = sessionFactory.openSession();
	}
	@After
	public void after(){
		session.close();
	}
	
	@Test
	public void testSelectAll(){
		try {
			TagMapper tm = session.getMapper(TagMapper.class);
			List<Tag> fList = tm.selectAll();
			Assert.assertEquals(3, fList.size());
			Assert.assertEquals(1, fList.get(0).getfList().size());
			Assert.assertEquals("淘宝",fList.get(0).getfList().get(0).getfLabel());
		} finally {
			session.close();
		}
	}
	

}

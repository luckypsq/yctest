package com.yc.favorite.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.biz.FavoriteBiz;

public class MapperTest {

	private SqlSession session;
	@Before
	private void before() throws IOException {
		String resource = "mybatis.xml";
		InputStream in = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
		session = sessionFactory.openSession();
	}
	@After
	public void after(){
		session.close();
	}
	
	@Test
	public void testSelectAll(){
		TagMapper tm = session.getMapper(TagMapper.class);
		System.out.println(tm.selectAll());
	}
	@Test
	public void testAddFavorite() {
		FavoriteBiz fb = new FavoriteBiz();
		Favorite favorite = new Favorite();
		favorite.setfLabel("淘宝");
		favorite.setfTags("购物；消费；支付");
		favorite.setfUrl("www.taobao.com");
		favorite.setfDesc("国内最大的购物网站");
		fb.addFavorite(favorite);
	}
}

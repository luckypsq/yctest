package com.yc.blog;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.bean.ArticleExample.Criteria;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.UserMapper;

@SpringBootTest
class BlogApplicationTests {
	
	@Resource
	private UserMapper um;
	
	@Resource
	private ArticleMapper am;
	
	@Test
	void contextLoads() {
		Assert.isTrue(um.selectByExample(null).size() > 0,"结果集数量不正确");
		
		//如何使用组合条件查询
		ArticleExample aExample = new ArticleExample();
		
		Criteria criteria = aExample.createCriteria();
		
		criteria.andCreatetimeIsNotNull();
		criteria.andTitleLike("%css%");
		
		List<Article> list = am.selectByExample(aExample);
		System.out.println(list);
		
	}
	
	@Test
	void test1() {
		//pageHelper 分页查询
	}
}
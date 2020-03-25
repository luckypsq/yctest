package com.yc.ebuy.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.yc.ebuy.bean.EasybuyNews;
import com.yc.ebuy.bean.EasybuyNewsExample;
import com.yc.ebuy.dao.EasybuyNewsMapper;

@RestController
public class NewsAction {

	@Resource
	private EasybuyNewsMapper enm;
	
	
	/**
	 * 查询所有的新闻
	 *	// 127.0.0.1:8002/getPc
	 */
	@GetMapping("getNews")
	public List<EasybuyNews> getPc(){
		EasybuyNewsExample ne = new EasybuyNewsExample();
		ne.setOrderByClause("id desc");
		PageHelper.startPage(1, 5);
		List<EasybuyNews> list = enm.selectByExample(ne);
		return list;
	}
}

package com.yc.blog.web;

import java.io.File;
import java.io.IOException;

/**
 * selectByExampleWithBLOBs 					用于长文本的查询
 * selectByExample 								用于简单数据的查询
 * ae.createCriteria().andCategoryidEqualTo(id);组合查询
 */

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.bean.User;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.CategoryMapper;
import com.yc.blog.vo.Result;

@Controller
public class IndexAction {

	@Resource
	private ArticleMapper am;
	@Resource
	private CategoryMapper cm;
	
	//程序初始化
	@ModelAttribute
	public void init(Model m) {
		//查询分类列表
		m.addAttribute("clist",cm.selectByExample(null));
	}
	
	@GetMapping({"/","index","index.html"})
	public String index(@RequestParam(defaultValue ="1") Integer pageInteger , Model m) {
		//分页
		Page<Article> pg = PageHelper.startPage(pageInteger,5);
		//查询所有
		am.selectByExampleWithBLOBs(null);
		m.addAttribute("alist",pg);
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andReadcntGreaterThanOrEqualTo(300);
		//查询热门文章(readCnt > 300)
		return "index";
	}
	/**
	 * 文章详情
	 * @param id
	 * @param m
	 * @return
	 */
	@GetMapping({"article"})
	public String article(Integer id,Model m) {
		//条件查询
		Article a = am.selectByPrimaryKey(id);
		m.addAttribute(a);
		return "article";
	}
	
	/**
	 * 分类查询
	 */
	@GetMapping("category")
	public String category(Integer id,@RequestParam(defaultValue ="1") Integer pageInteger ,Model m) {
		Page<Article> pg = PageHelper.startPage(pageInteger,5);
		ArticleExample ae = new ArticleExample();
		
		ae.createCriteria().andCategoryidEqualTo(id);
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("alist",pg);
		m.addAttribute("id",id);
		return "category";
	}
	
	/**
	 * 注册
	 */
	@GetMapping("toreg")
	public String toreg() {
		return "reg";
	}
	/**
	 * ajax方法必须使用注解
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	//获取yml配置的值
	@Value("${spring.resources.static-locations}")
	private String[] staticLocation;
	//获取文件上传的地址
	private String getMyPath() {
		return staticLocation[2].substring("file:".length());
	}
	@PostMapping("reg")
	@ResponseBody
	public Result reg(User user,@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(user);
		file.transferTo(new File(getMyPath() + file.getOriginalFilename()));
		return new Result("用户注册成功！",0);
	}
}

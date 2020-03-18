package com.yc.blog.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * selectByExampleWithBLOBs 					用于长文本的查询
 * selectByExample 								用于简单数据的查询
 * ae.createCriteria().andCategoryidEqualTo(id);组合查询
 */

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yc.blog.bean.Article;
import com.yc.blog.bean.ArticleExample;
import com.yc.blog.bean.User;
import com.yc.blog.biz.BaseBizException;
import com.yc.blog.biz.UserBiz;
import com.yc.blog.dao.ArticleMapper;
import com.yc.blog.dao.CategoryMapper;
import com.yc.blog.dao.FlinkMapper;
import com.yc.blog.dao.UserMapper;
import com.yc.blog.vo.Result;

@Controller
public class IndexAction {

	@Resource
	private ArticleMapper am;
	@Resource
	private CategoryMapper cm;
	@Resource
	private FlinkMapper fm;
	@Resource
	private UserMapper um;

	// 程序初始化
	@ModelAttribute
	public void init(@RequestParam(defaultValue = "1") Integer page, Model m) {
		// 查询热门文章(readCnt > 300)
		Page<Article> pg1 = PageHelper.startPage(page, 10);
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andReadcntGreaterThanOrEqualTo(300);
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("hotlist", pg1);
		// 查询分类列表
		m.addAttribute("clist", cm.selectByExample(null));
	}

	@GetMapping({ "/", "index", "index.html" })
	public String index(@RequestParam(defaultValue = "1") Integer page, Model m) {
		// 分页
		Page<Article> pg = PageHelper.startPage(page, 5);
		// 查询所有
		ArticleExample ae = new ArticleExample();
		ae.setOrderByClause("createtime desc");
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("alist", pg);
		return "index";
	}

	/**
	 * 文章详情
	 * 
	 * @param id
	 * @param m
	 * @return
	 */
	@GetMapping({ "article" })
	public String article(Integer id, Model m) {
		// 条件查询
		Article a = am.selectByPrimaryKey(id);
		m.addAttribute(a);
		return "article";
	}

	/**
	 * 分类查询
	 */
	@GetMapping("category")
	public String category(Integer id, @RequestParam(defaultValue = "1") Integer page, Model m) {
		Page<Article> pg = PageHelper.startPage(page, 5);
		ArticleExample ae = new ArticleExample();

		ae.createCriteria().andCategoryidEqualTo(id);
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("alist", pg);
		m.addAttribute("id", id);
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
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	// 获取yml配置的值
	@Value("${myUploadPath}")
	private String myUploadPath;

	@Resource
	private UserBiz ubiz;

	@PostMapping("reg")
	@ResponseBody
	public Result reg(@Valid User user, Errors errors, @RequestParam("file") MultipartFile file, String repwd)
			throws IllegalStateException, IOException {
		if (errors.hasFieldErrors()) {
			return new Result("用户失败！", 0, errors.getFieldErrors());
		}
		file.transferTo(new File(myUploadPath + file.getOriginalFilename()));
		try {
			String head = "/" + file.getOriginalFilename();
			user.setHead(head);
			ubiz.reg(user, repwd);
			return new Result("注册成功！", 1);
		} catch (BaseBizException e) {
			e.printStackTrace();
			errors.rejectValue(e.getName(), "" + e.getCode(), e.getMessage());
			return new Result("注册失败！", e.getCode(), errors.getFieldErrors());
		}
	}

	/**
	 * 用户可以根据用户名，昵称，电话，邮箱进行登录
	 */
	@PostMapping("login")
	@ResponseBody
	public Result login(@Valid User user, Errors errors, HttpSession session) {
		if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
			return new Result("请输入用户名和密码！", 0);
		}
		try {
			User dbUser = ubiz.login(user);
			session.setAttribute("loginedUser", dbUser);
			return new Result("登录成功！", 1, dbUser);
		} catch (BaseBizException e) {
			e.printStackTrace();
			return new Result(e.getMessage(), e.getCode());
		}
	}

	/**
	 * 友情链接
	 */
	@GetMapping("links")
	public String flinks(Model m) {
		// 查询所有
		m.addAttribute("flist", fm.selectByExample(null));
		return "links";
	}

	/**
	 * 编辑文章
	 */
	@GetMapping("editArticle")
	public String toEdit() {
		return "article-add";
	}

	/**
	 * 保存编辑
	 */
	@PostMapping("saveArticle")
	public String saveArticle(Article a, @SessionAttribute("loginedUser") User user) {
		a.setAuthor(user.getName());
		a.setCreatetime(new Date());
		am.insert(a);
		// 响应重定向
		return "redirect:article?id=" + a.getId();
	}

	/**
	 * 找回密码
	 */
	@GetMapping("toforget")
	public String toforget() {
		return "forget";
	}

	/**
	 * 保存验证码
	 */
	@PostMapping("sendVcode")
	@ResponseBody
	public Result sendVcode(String account, HttpSession session) {
		try {
			String vcode = ubiz.foget(account);
			session.setAttribute("vcode", vcode);
			return new Result("验证码发送成功！", 1);
		} catch (BaseBizException e) {
			e.printStackTrace();
			return new Result(e.getMessage(), 0);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new Result("邮件发送失败，请联系客服", 0);
		}
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @param repwd
	 * @param vcode
	 * @param sessionVcode
	 * @param userOld
	 * @param session
	 * @return
	 */
	@PostMapping("changePwd")
	@ResponseBody
	public Result changePwd(User user, String repwd, String vcode, @SessionAttribute("vcode") String sessionVcode,
			@SessionAttribute("loginedUser") User userOld, HttpSession session) {
		if (sessionVcode.equals(vcode)) {
			if (user.getPwd().equals(repwd)) {
				userOld.setPwd(user.getPwd());
				um.updateByPrimaryKey(userOld);
				session.setAttribute("vcode", "");
				return new Result("修改成功！！！", 1);
			} else {
				return new Result("两次密码不一致！", 101);
			}
		} else {
			return new Result("验证码输入错误！", 103);
		}
	}

	/**
	 * 搜索
	 */
	@PostMapping("search")
	@ResponseBody
	public Result search(String keyword, @RequestParam(defaultValue = "1") Integer page, Model m) {
		// 分页
		Page<Article> pg = PageHelper.startPage(page, 5);
		// 查询所有
		ArticleExample ae = new ArticleExample();
		ae.createCriteria().andKeywordsEqualTo(keyword);
		ae.setOrderByClause("createtime desc");
		am.selectByExampleWithBLOBs(ae);
		m.addAttribute("alist", pg);
		if(pg.size() > 0) {
			return new Result("查询成功！",1);
		}else {
			return new Result("查询失败！",0);
		}
		

	}
}

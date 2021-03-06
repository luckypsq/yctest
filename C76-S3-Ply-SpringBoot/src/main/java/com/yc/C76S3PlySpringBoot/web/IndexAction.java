package com.yc.C76S3PlySpringBoot.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.C76S3PlySpringBoot.biz.FavoriteBiz;
import com.yc.C76S3PlySpringBoot.dao.FavoriteMapper;
import com.yc.C76S3PlySpringBoot.dao.TagMapper;
import com.ycC76S3PlySpringBoot.bean.Favorite;
import com.ycC76S3PlySpringBoot.bean.Tag;

@Controller
public class IndexAction {
	@Resource
	private TagMapper tm;
	@Autowired
	private FavoriteMapper fm;
	
	FavoriteBiz fBiz = new FavoriteBiz();
	
	@GetMapping("index.s")
	public String index(String tId,String flag, Model m) {
		List<Tag> list = tm.selectAll();
		
		if(tId != null){
			for (Tag tag : list) {
				//判断tId参数值是否 == 当前的标签tID
				if(tId.equals(""+tag.gettId())){
					m.addAttribute("showTag",tag);
					break;
				}
			}
		}else if(flag != null){
			List<Favorite> fList = fm.selectByFlag(flag);
			m.addAttribute("fList",fList);
		}
		m.addAttribute("tList",list);
		return "index";
	}
	@RequestMapping("saveFavorite.s")
	public String addFavorite(Favorite f,Model m){
		try {
			fBiz.addFavorite(f);
			//响应重定向
			return "redirect:index.s";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("msg", "添加失败");
			return "edit";
		}
	}
	@RequestMapping("toedit")
	public String toEdit(){
		return "edit";
	}
}

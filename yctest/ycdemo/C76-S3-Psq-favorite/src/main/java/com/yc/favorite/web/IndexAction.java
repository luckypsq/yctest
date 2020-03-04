package com.yc.favorite.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;

@Controller
public class IndexAction {
	@Resource
	private TagMapper tm;
	@Autowired
	private FavoriteMapper fm;
	
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
}

package com.yc.favorite.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.bean.Tag;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SqlSession session = MyBatisHelper.getSession();
		
		try {
			TagMapper tMapper = session.getMapper(TagMapper.class);
			FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
			List<Tag> list = tMapper.selectAll();
			
			/**
			 * 查找要展示的标签
			 */
			String tId = request.getParameter("tId");
			String flag = request.getParameter("flag");
			if(tId != null){
				for (Tag tag : list) {
					//判断tId参数值是否 == 当前的标签tID
					if(tId.equals(""+tag.gettId())){
						request.setAttribute("showTag", tag);
						break;
					}
				}
			}else if(flag != null){
				List<Favorite> fList = fm.selectByFlag(flag);
				request.setAttribute("fList", fList);
			}
			
			
			request.setAttribute("tList", list);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} finally {
			session.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

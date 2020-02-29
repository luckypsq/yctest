package com.yc.favorite.biz;

import org.apache.ibatis.session.SqlSession;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

public class FavoriteBiz {
	
	public void addFavorite(Favorite f){
		
		SqlSession session = MyBatisHelper.getSession();
		try {
			TagMapper tm = session.getMapper(TagMapper.class);
			FavoriteMapper fm = session.getMapper(FavoriteMapper.class);
			fm.insert(f);
			String[] tags = f.getfTags().split("[,，；;\\s]");
			for(String tag : tags){
				int ret = tm.updateCount(tag);
				if( ret == 0){
					tm.insert(tag);
				}
			}
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			session.rollback();
		}finally{
			session.close();
		}
		
		
	}

}

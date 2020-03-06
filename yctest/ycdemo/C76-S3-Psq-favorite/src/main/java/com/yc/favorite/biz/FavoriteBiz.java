package com.yc.favorite.biz;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.favorite.bean.Favorite;
import com.yc.favorite.dao.FavoriteMapper;
import com.yc.favorite.dao.TagMapper;
import com.yc.favorite.util.MyBatisHelper;

@Service
@Transactional
public class FavoriteBiz {
	@Resource
	TagMapper tm ;
	@Resource
	FavoriteMapper fm;
	public void addFavorite(Favorite f){
			fm.insert(f);
			String[] tags = f.getfTags().split("[,，；;\\s]");
			for(String tag : tags){
				int ret = tm.updateCount(tag);
				if( ret == 0){
					tm.insert(tag);
				}
			}
	}

}

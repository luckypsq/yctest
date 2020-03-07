package com.yc.C76S3PlySpringBoot.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.C76S3PlySpringBoot.dao.FavoriteMapper;
import com.yc.C76S3PlySpringBoot.dao.TagMapper;
import com.ycC76S3PlySpringBoot.bean.Favorite;

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

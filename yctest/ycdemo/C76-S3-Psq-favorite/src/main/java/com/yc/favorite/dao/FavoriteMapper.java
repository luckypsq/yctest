package com.yc.favorite.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.yc.favorite.bean.Favorite;

public interface FavoriteMapper {
	
	public List<Favorite> selectByFlag(@Param("flag")String flag);
	
	@Insert("insert into favorite values (null,#{fLabel},#{fUrl},#{fTags},#{fDesc})")
	public void insert(Favorite f);

}

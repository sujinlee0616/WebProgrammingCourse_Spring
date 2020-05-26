package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Select;

public interface MusicMapper {
	@Select("SELECT * FROM music_genie ORDER BY mno ASC")
	public List<MusicVO> musicListData();
	
	@Select("SELECT * FROM music_genie "
			+ "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
	
	@Select("SELECT COUNT(*) FROM member "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	@Select("SELECT pwd FROM member "
			+ "WHERE id=#{id}")
	public String memberGetPassword(String id);
}

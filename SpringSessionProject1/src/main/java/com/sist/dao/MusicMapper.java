package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;

public interface MusicMapper {
	
	// 아래의 메소드들을 DAO에서 구현
	
	// [목록출력]
	@Select("SELECT * FROM music_genie ORDER BY mno ASC")
	public List<MusicVO> musicListData();
	
	// [상세보기]
	@Select("SELECT * FROM music_genie "
			+ "WHERE mno=#{mno}")
	public MusicVO musicDetailData(int mno);
	
	// [아이디 존재여부 확인]
	@Select("SELECT COUNT(*) FROM member "
			+ "WHERE id=#{id}")
	public int idCount(String id);
	
	// [비번확인]
	@Select("SELECT pwd FROM member "
			+ "WHERE id=#{id}")
	public String memberGetPassword(String id);
	
	
	
}


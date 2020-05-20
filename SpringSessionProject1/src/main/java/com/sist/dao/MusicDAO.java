package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 메모리 할당 
public class MusicDAO {
	
	@Autowired
	private MusicMapper mapper;
	
	// DAO ==> 메소드 구현
	
	public List<MusicVO> musicListData()
	{
		return mapper.musicListData();
	}
	
	public MusicVO musicDetailData(int mno)
	{
		return mapper.musicDetailData(mno);
	}

	public int idCount(String id)
	{
		return mapper.idCount(id);
	}
	
	public String memberGetPassword(String id)
	{
		return mapper.memberGetPassword(id);
	}
	
	
}

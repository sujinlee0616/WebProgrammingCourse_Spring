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
	
	/* 
	 * WEB-INF > config > applictation-datasource.xml 에서 Mapper를 패키지 단위로 등록 
	 * ===> 아래와 같이 하면 됨. 
	 * 
	 * interface A
	 * interface B
	 * class MapperFactoryBean implements A,B 
	 * 
	 * ===> 그런데, 같은 해키지내에 Interface가 있다면 applictation-datasource.xml에 패키지 단위로 등록하면 안 되고 개별 Mapper로 등록해야함. 
	 * 
	 */
	
	
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

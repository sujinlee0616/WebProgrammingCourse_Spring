package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.*;

// JDBC => ORM 

@Repository
public class MovieDAO {
	@Autowired
	private MovieMapper mapper;
	// Autowired Annotation이 없으면 그냥 인터페이스임. 
	// ★★★ Autowired 또는 Resource Annotation이 있으면 구현이 됨. ★★★
	
	public List<MovieVO> movieListData(Map map)
	{
		return mapper.movieListData(map);
	}
	
	public int movieTotalPage(int type)
	{
		return mapper.movieTotalPage(type);
	}
	
	public MovieVO movieDetailData(int mno)
	{
		return mapper.movieDetailData(mno);
	}
	
	
}








package com.sist.dao;

import com.mongodb.*;
import java.util.*;
import org.springframework.stereotype.Repository;

//★★★ [MongoDB] ★★★
@Repository
public class MovieDAO {
	private MongoClient mc; // Connection
	private DB db; // DataBase(XE)
	private DBCollection dbc; // Table
	public MovieDAO()
	{
		try {
			mc=new MongoClient("localhost",27017); // Oracle의 1521처럼 MongoDB는 27017
			db=mc.getDB("mydb"); // 우리가 저번에 이름을 mydb로 만들어놨으니까..
			dbc=db.getCollection("movie");
		} catch (Exception ex) {}
	}
	
	// JSON형식: {mno:100,title:'',...} 이렇게 한 블록이 한 row 임. <== 얘네를 MongoDB에서는 'BasicDBObject'라고 한다. 
	public void movieInsert(MovieVO vo)
	{
		try 
		{
			BasicDBObject obj=new BasicDBObject();
			obj.put("mno", vo.getMno());
			obj.put("title", vo.getTitle());
			obj.put("poster", vo.getPoster());
			obj.put("director", vo.getDirector());
			obj.put("actor", vo.getActor());
			obj.put("genre", vo.getGenre());
			obj.put("grade", vo.getGrade());
			obj.put("story", vo.getStory());
			dbc.insert(obj);
			
		} catch (Exception ex) {}
	}
	

	public List<MovieVO> movieListData(int page)
	{
		List<MovieVO> list=new ArrayList<MovieVO>();
		try {
			int rowSize=12;
			int skip_data=rowSize*(page-1); // 이번 페이지: 이전 페이지까지 나온 데이터들 개수(int skip)는 스킵하고 그 뒤의 데이터부터 노출
			DBCursor cursor=dbc.find().skip(skip_data).limit(rowSize); // DBCursor = ResultSet
			// 이전 페이지까지의 데이터는 버리고 (skip), rowSize 개수만큼만 들고와라 
			
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				
				list.add(vo);
				
				// [참고]
				// - MongoDB는 특정 조건을 지정(ex. 특정 컬럼만 갖고와라)해서 DB를 가겨올 수 없다. 그냥 다 가져와야함.  
			}
			
		} catch (Exception ex) {}
		
		return list;
	}
	
}







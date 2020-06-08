package com.sist.dao;

import com.mongodb.*;
import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

//★★★ [MongoDB] ★★★
//- NoSQL (ex.MongoDB)임. 
//- SQL이 존재하지 않고 ★함수★를 이용해서 처리.
//- Documentation: https://docs.mongodb.com/v4.0/crud/
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
			dbc=db.getCollection("movie2");
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
	
	// 목록 총 페이지  
	public int movieTotalPage()
	{
		int total=0;
		try
		{
			// SELECT CEIL(COUNT(*)/12.0) FROM movie
			int count=(int)dbc.count();
			// SELECT COUNT(*) FROM movie 
			total=(int)(Math.ceil(count/12.0)); 
		}catch (Exception ex) {}
		return total;
	}
	
	
	// ★★★[MongoDB 사용 검색]★★★ 
	public List<MovieVO> movieFindData(String search_keyword)
	{
		// [Oracle] Oracle이었다면 아래와 같이 코딩 
		// SELECT * FROM movie WHERE title LIKE '%search_keyword%'
		
		// [MongoDB] MongoDB는 아래와 같이 코딩 (정규식 사용) - 이렇게 짜는건 JDBC 버젼임. MyBatis 버젼도 있음. (spring-mongoDB) 
		// find({"title",{"$regex",".*"+search_keyword}})
		//               ==============================
		//				  BasicDBObject    얘는 Oracle에서의 "LIKE '%search_keyword%'" 와 같다.
		//      =======================================
		// 		BasicDBObject
		// find({no:1}) 이런식으로 넘어간다. 
		
		List<MovieVO> list=new ArrayList<MovieVO>();
		try {
			BasicDBObject where=new BasicDBObject("title",new BasicDBObject("$regex",".*"+search_keyword)); // ex) '터' 들어가는 제목 찾아라 
			DBCursor cursor=dbc.find(where);
			// [Java] ResultSet = [MongoDB] DBCursor
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next(); // rs.next()
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				list.add(vo);
			}
			cursor.close();

		} catch (Exception ex) {}
		return list;
	}
	
	// [상세보기]
	public MovieVO movieDetailData(int mno)
	{
		MovieVO vo=new MovieVO();
		
		try 
		{
			BasicDBObject where=new BasicDBObject("mno",mno); // ex) mno=10 인 애 찾아라 
			BasicDBObject res=(BasicDBObject)dbc.findOne(where); // findOne: 데이터 한 개 찾아라
			vo.setMno(res.getInt("mno")); // vo의 mno를 res의 mno(key)의 value로 설정해라. 
			vo.setTitle(res.getString("title"));
			vo.setActor(res.getString("actor"));
			vo.setDirector(res.getString("director"));
			vo.setPoster(res.getString("poster"));
			vo.setGenre(res.getString("genre"));
			vo.setGrade(res.getString("grade"));
			vo.setStory(res.getString("story"));
		} catch (Exception ex) {}
		
		return vo;
	}
	
}









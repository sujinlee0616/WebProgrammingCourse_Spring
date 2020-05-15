package com.sist.dao;

import com.mongodb.*;
import java.util.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

//�ڡڡ� [MongoDB] �ڡڡ�
// - NoSQL (ex.MongoDB)��. 
// - SQL�� �������� �ʰ� ���Լ��ڸ� �̿��ؼ� ó��.
// - Documentation: https://docs.mongodb.com/v4.0/crud/
@Repository
public class MovieDAO {
	private MongoClient mc; // Connection
	private DB db; // DataBase(XE)
	private DBCollection dbc; // Table
	public MovieDAO()
	{
		try {
			mc=new MongoClient("localhost",27017); // Oracle�� 1521ó�� MongoDB�� 27017
			db=mc.getDB("mydb"); // �츮�� ������ �̸��� mydb�� ���������ϱ�..
			dbc=db.getCollection("movie");
		} catch (Exception ex) {}
	}
	
	// JSON����: {mno:100,title:'',...} �̷��� �� ������ �� row ��. <== ��׸� MongoDB������ 'BasicDBObject'��� �Ѵ�. 
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
			int skip_data=rowSize*(page-1); // �̹� ������: ���� ���������� ���� �����͵� ����(int skip)�� ��ŵ�ϰ� �� ���� �����ͺ��� ����
			DBCursor cursor=dbc.find().skip(skip_data).limit(rowSize); // DBCursor = ResultSet
			// ���� ������������ �����ʹ� ������ (skip), rowSize ������ŭ�� ����Ͷ� 
			
			while(cursor.hasNext())
			{
				BasicDBObject obj=(BasicDBObject)cursor.next();
				MovieVO vo=new MovieVO();
				vo.setMno(obj.getInt("mno"));
				vo.setTitle(obj.getString("title"));
				vo.setPoster(obj.getString("poster"));
				
				list.add(vo);
				
				// [����]
				// - MongoDB�� Ư�� ������ ����(ex. Ư�� �÷��� �����Ͷ�)�ؼ� DB�� ���ܿ� �� ����. �׳� �� �����;���.  
			}
			
		} catch (Exception ex) {}
		
		return list;
	}
	
	// ��� �� ������  
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
	
	
	// �ڡڡ�[MongoDB ��� �˻�]�ڡڡ� 
	public List<MovieVO> movieFindData(String search_keyword)
	{
		// [Oracle] Oracle�̾��ٸ� �Ʒ��� ���� �ڵ� 
		// SELECT * FROM movie WHERE title LIKE '%search_keyword%'
		
		// [MongoDB] MongoDB�� �Ʒ��� ���� �ڵ� (���Խ� ���) - �̷��� ¥�°� JDBC ������. MyBatis ������ ����. (spring-mongoDB) 
		// find({"title",{"$regex",".*"+search_keyword}})
		//               ==============================
		//				  BasicDBObject    ��� Oracle������ "LIKE '%search_keyword%'" �� ����.
		//      =======================================
		// 		BasicDBObject
		// find({no:1}) �̷������� �Ѿ��. 
		
		List<MovieVO> list=new ArrayList<MovieVO>();
		try {
			BasicDBObject where=new BasicDBObject("title",new BasicDBObject("$regex",".*"+search_keyword)); // ex) '��' ���� ���� ã�ƶ� 
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
	
	// [�󼼺���]
	public MovieVO movieDetailData(int mno)
	{
		MovieVO vo=new MovieVO();
		
		try 
		{
			BasicDBObject where=new BasicDBObject("mno",mno); // ex) mno=10 �� �� ã�ƶ� 
			BasicDBObject res=(BasicDBObject)dbc.findOne(where); // findOne: ������ �� �� ã�ƶ� 
			vo.setMno(res.getInt("mno")); // vo�� mno�� res�� mno(key)�� value�� �����ض�. 
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








package com.sist.recipe;
import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;

import java.io.*;
import java.util.*;

public class RecipeDAO {
	private static SqlSessionFactory ssf;
	static
	{
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
    
	public static void chefInsert(ChefVO vo)
	{
		SqlSession session = ssf.openSession(true); // 오토커밋
		session.insert("chefInsert", vo);  // chefInsert에 vo를 넣는다
		
		session.close();
	}
	
	public static void recipeInsert(RecipeVO vo){
		
		SqlSession session = ssf.openSession(true); 
		session.insert("recipeInsert", vo);  
		session.close();
	}
	
	public static void recipeDetailInsert(RecipeDetailVO vo)
	{
		SqlSession session = ssf.openSession(true); 
		session.insert("recipeDetailInsert", vo);  
		session.close();
	}
	
	public static List<RecipeVO> recipeData()
	{
		SqlSession session = ssf.openSession(); // auto 커밋X
		List<RecipeVO> list = session.selectList("recipeData");
		session.close();
		
		return list;
	}
	
	
	
}














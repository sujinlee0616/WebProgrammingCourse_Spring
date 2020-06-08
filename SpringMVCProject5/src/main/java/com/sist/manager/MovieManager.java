package com.sist.manager;

import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.MovieDAO;
import com.sist.dao.MovieVO;

//[네이버 영화 크롤링]
//main 돌리면 MongoDB에 쌓임 
public class MovieManager {
	
	public List<String> movieLinkData()
	{
		List<String> list=new ArrayList<String>();
		
		try 
		{
			for(int i=1;i<=40;i++) // 총 40페이지 	
			{
				Document doc=Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20200608&page="+i).get();
				Elements link=doc.select("td.title div.tit5 a");
				for(int j=0;j<link.size();j++)
				{
					String strLink=link.get(j).attr("href");
					list.add("https://movie.naver.com"+strLink);
				}
			}
			
			
		} catch (Exception ex) {}
		
		return list;
	}
	
	public void movieAllData()
	{
		try {
			
			// DAO 연결
			MovieDAO dao=new MovieDAO();
			
			List<String> list=movieLinkData();
			int k=1;
			for(String url:list)
			{
				// 성인영화는 크롤링 불가 ==> 크롤링 불가한 애들 이후에도 크롤링 되게 하기 위해서 for문 안에 try~catch를 이렇게 달아줬음  
				try {
					
					Document doc=Jsoup.connect(url).get();
					String code=url.substring(url.lastIndexOf("=")+1);
					
					Element title=doc.selectFirst("div.mv_info h3.h_movie a");
					// poster: https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode=xxxxx
					String poster="https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode="+code;
					
					Element genre=doc.selectFirst("dl.info_spec span a");
					Element grade=doc.select("dl.info_spec dd").get(3);
					Element director=doc.selectFirst("div.info_spec2 dl.step1 a");
					Element actor=doc.selectFirst("div.info_spec2 dl.step2 a");
					Element story=doc.selectFirst("div.video div.story_area p.con_tx");
					
					MovieVO vo=new MovieVO();
					vo.setMno(Integer.parseInt(url.substring(url.lastIndexOf("=")+1))); 
					// <a href="/movie/bi/mi/basic.nhn?code=174830" title="가버나움"> 에서 href의 = 뒤의 숫자 긁음
					// 영화 고유번호 
					System.out.println("================== 영화정보 ==================");
					System.out.println("k="+k);
					System.out.println("1.영화번호="+vo.getMno());
					System.out.println("2.제목="+title.text());
					System.out.println("3.포스터="+poster);
					System.out.println("4.장르="+genre.text());
					System.out.println("5.관람등급="+grade.text());
					System.out.println("6.감독="+director.text());
					System.out.println("7.배우="+actor.text());
					System.out.println("8.줄거리="+story.text());
					
					vo.setTitle(title.text());
					vo.setPoster(poster);
					vo.setGenre(genre.text());
					vo.setGrade(grade.text());
					vo.setDirector(director.text());
					vo.setActor(actor.text());
					vo.setStory(story.text());
					
					// insert
					dao.movieInsert(vo);
					
					k++;
					
				} catch (Exception ex) {}
				
				
			}
		} catch (Exception ex) {}
	}

	public static void main(String[] args) {
		MovieManager m=new MovieManager();
		m.movieAllData();
	}
}






